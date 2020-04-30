#!/bin/bash
# shellcheck disable=SC2009
# 查找运行的项目
export JAVA_HOME="/usr/java/jdk-11.0.6"
export PATH=.:$JAVA_HOME/bin:$PATH
rm -rf /data/weblogs/nest/*
PID=$(ps -ef | grep java | grep -v grep | grep 'nest.jar' | awk '{print $2}')
# 拉取代码、编译打包
cd /data/src/nest || return
echo "项目目录为： " 'pwd'
echo "pull 最新代码。"
git reset --hard origin/master
git clean -f
git pull
echo "拉取代码成功"

# 打包最新的项目
mvn clean
mvn compile
mvn package -U

# 结束运行的项目
if [ "${PID}" == "" ]; then
	echo "项目未运行."
else
	echo "进程：${PID}"
	kill -9 "${PID}"
	echo "项目已关闭."
fi
# 更新 jar 包
cd /data/src/nest/target/ || return
ls -la
echo "需要更新项目，删除原项目"
rm -f /webroot/packages/nest.jar
echo "原项目已删除，部署新项目"
mv /data/src/nest/target/nest-*.jar /webroot/packages/nest.jar

# 启动程序
echo "启动项目中..."
nohup nohup java -jar /webroot/packages/nest.jar >/data/weblogs/nest/nest_start.log 2>&1 &
sleep 10
echo "项目已启动，打开运行日志"
tail -256 /data/weblogs/nest/nest_start*
cd /data/src/nest/ || return
mvn clean
exit 0

#!/usr/bin/env bash
time=$(date "+%Y-%m-%d %H:%M:%S")
echo "当前用户是： ${USER}"
echo "当前时间是： ${time}"
git pull
git add -A
git commit -m "${USER} 更新于: ${time}."
git push
echo "更新并推送成功！"
exit 0;



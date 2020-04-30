var geoCoordMap = {
	'上海市': [121.487, 31.2491],
	'临沧市': [100.092, 23.8878],
	'丽江市': [100.229, 26.8753],
	'保山市': [99.1779, 25.1204],
	'大理白族自治州': [100.223, 25.5968],
	'德宏傣族景颇族自治州': [98.5894, 24.4412],
	'怒江傈僳族自治州': [98.8599, 25.8606],
	'文山壮族苗族自治州': [104.246, 23.3740],
	'昆明市': [102.714, 25.0491],
	'昭通市': [103.725, 27.3406],
	'普洱市': [100.980, 22.7887],
	'曲靖市': [103.782, 25.5207],
	'楚雄彝族自治州': [101.529, 25.0663],
	'玉溪市': [102.545, 24.3704],
	'红河哈尼族彝族自治州': [103.384, 23.3677],
	'西双版纳傣族自治州': [100.803, 22.0094],
	'迪庆藏族自治州': [99.7136, 27.8310],
	'乌兰察布市': [113.112, 41.0223],
	'乌海市': [106.831, 39.6831],
	'兴安盟': [122.048, 46.0837],
	'包头市': [109.846, 40.6471],
	'呼伦贝尔市': [119.760, 49.2016],
	'呼和浩特市': [111.660, 40.8283],
	'巴彦淖尔市': [107.423, 40.7691],
	'赤峰市': [118.930, 42.2971],
	'通辽市': [122.260, 43.6337],
	'鄂尔多斯市': [109.993, 39.8164],
	'锡林郭勒盟': [116.027, 43.9397],
	'延边朝鲜族自治州': [129.485, 42.8964],
	'广元市': [105.819, 32.4410],
	'阿拉善盟': [105.695, 38.8430],
	'北京市': [116.395, 39.9299],
	'台中市': [119.337, 26.0911],
	'长春市': [125.313, 43.8983],
	'台北市': [114.130, 22.3748],
	'台南市': [121.360, 38.9658],
	'嘉义市': [114.246, 22.7288],
	'白山市': [126.435, 41.9458],
	'高雄市': [111.590, 21.9464],
	'吉林市': [126.564, 43.8719],
	'四平市': [124.391, 43.1755],
	'松原市': [124.832, 45.1360],
	'白城市': [122.840, 45.6210],
	'辽源市': [125.133, 42.9233],
	'通化市': [125.942, 41.7363],
	'乐山市': [103.760, 29.6009],
	'内江市': [105.073, 29.5994],
	'凉山彝族自治州': [102.259, 27.8923],
	'南充市': [106.105, 30.8009],
	'宜宾市': [104.633, 28.7696],
	'巴中市': [106.757, 31.8691],
	'广安市': [106.635, 30.4639],
	'德阳市': [104.402, 31.1311],
	'成都市': [104.067, 30.6799],
	'攀枝花市': [101.722, 26.5875],
	'泸州市': [105.443, 28.8959],
	'甘孜藏族自治州': [101.969, 30.0551],
	'眉山市': [103.841, 30.0611],
	'绵阳市': [104.705, 31.5047],
	'自贡市': [104.776, 29.3591],
	'资阳市': [104.635, 30.1321],
	'达州市': [107.494, 31.2141],
	'遂宁市': [105.564, 30.5574],
	'阿坝藏族羌族自治州': [102.228, 31.9057],
	'雅安市': [103.009, 29.9997],
	'天津市': [117.210, 39.1439],
	'中卫市': [105.196, 37.5211],
	'吴忠市': [106.208, 37.9935],
	'固原市': [106.285, 36.0215],
	'石嘴山市': [106.379, 39.0202],
	'银川市': [106.206, 38.5026],
	'亳州市': [115.787, 33.8712],
	'六安市': [116.505, 31.7555],
	'合肥市': [117.282, 31.8669],
	'安庆市': [117.058, 30.5378],
	'宣城市': [118.752, 30.9516],
	'宿州市': [116.988, 33.6367],
	'池州市': [117.494, 30.6600],
	'淮北市': [116.791, 33.9600],
	'淮南市': [117.018, 32.6428],
	'滁州市': [118.324, 32.3173],
	'芜湖市': [118.384, 31.3660],
	'蚌埠市': [117.357, 32.9294],
	'铜陵市': [117.819, 30.9409],
	'阜阳市': [115.820, 32.9012],
	'马鞍山市': [118.515, 31.6885],
	'黄山市': [118.293, 29.7344],
	'东营市': [118.583, 37.4871],
	'临沂市': [118.340, 35.0724],
	'威海市': [122.093, 37.5287],
	'德州市': [116.328, 37.4608],
	'日照市': [119.507, 35.4202],
	'枣庄市': [117.279, 34.8078],
	'泰安市': [117.089, 36.1880],
	'济南市': [117.024, 36.6827],
	'济宁市': [116.600, 35.4021],
	'淄博市': [118.059, 36.8046],
	'滨州市': [117.968, 37.4053],
	'潍坊市': [119.142, 36.7161],
	'烟台市': [121.309, 37.5365],
	'聊城市': [115.986, 36.4558],
	'莱芜市': [117.684, 36.2336],
	'菏泽市': [115.463, 35.2624],
	'青岛市': [120.384, 36.1052],
	'临汾市': [111.538, 36.0997],
	'吕梁市': [111.143, 37.5273],
	'大同市': [113.290, 40.1137],
	'太原市': [112.550, 37.8902],
	'忻州市': [112.727, 38.4610],
	'晋中市': [112.738, 37.6933],
	'晋城市': [112.867, 35.4998],
	'朔州市': [112.479, 39.3376],
	'运城市': [111.006, 35.0388],
	'长治市': [113.120, 36.2016],
	'阳泉市': [113.569, 37.8695],
	'东莞市': [113.763, 23.0430],
	'中山市': [113.422, 22.5451],
	'云浮市': [112.050, 22.9379],
	'佛山市': [113.134, 23.0350],
	'广州市': [113.307, 23.1200],
	'惠州市': [114.410, 23.1135],
	'揭阳市': [116.379, 23.5479],
	'梅州市': [116.126, 24.3045],
	'汕头市': [116.728, 23.3839],
	'汕尾市': [115.372, 22.7787],
	'江门市': [113.078, 22.5751],
	'河源市': [114.713, 23.7572],
	'深圳市': [114.025, 22.5460],
	'清远市': [113.040, 23.6984],
	'湛江市': [110.365, 21.2574],
	'潮州市': [116.630, 23.6618],
	'珠海市': [113.562, 22.2569],
	'肇庆市': [112.479, 23.0786],
	'茂名市': [110.931, 21.6682],
	'阳江市': [111.977, 21.8715],
	'韶关市': [113.594, 24.8029],
	'北海市': [109.122, 21.4727],
	'南宁市': [108.297, 22.8064],
	'崇左市': [107.357, 22.4154],
	'来宾市': [109.231, 23.7411],
	'柳州市': [109.422, 24.3290],
	'桂林市': [110.260, 25.2629],
	'梧州市': [111.305, 23.4853],
	'河池市': [108.069, 24.6995],
	'玉林市': [110.151, 22.6439],
	'百色市': [106.631, 23.9015],
	'贵港市': [109.613, 23.1033],
	'贺州市': [111.552, 24.4110],
	'钦州市': [108.638, 21.9733],
	'防城港市': [108.351, 21.6173],
	'乌鲁木齐市': [87.5649, 43.8403],
	'伊犁哈萨克自治州': [81.2978, 43.9222],
	'克孜勒苏柯尔克孜自治州': [76.1375, 39.7503],
	'克拉玛依市': [84.8811, 45.5943],
	'博尔塔拉蒙古自治州': [82.0524, 44.9136],
	'吐鲁番地区': [89.1815, 42.9604],
	'和田地区': [79.9302, 37.1167],
	'哈密地区': [93.5283, 42.8585],
	'喀什地区': [75.9929, 39.4706],
	'塔城地区': [82.9748, 46.7586],
	'昌吉回族自治州': [87.2960, 44.0070],
	'自治区直辖': [85.6148, 42.1270],
	'阿克苏地区': [80.2698, 41.1717],
	'阿勒泰地区': [88.1379, 47.8397],
	'南京市': [118.778, 32.0572],
	'南通市': [120.873, 32.0146],
	'宿迁市': [118.296, 33.9520],
	'常州市': [119.981, 31.7713],
	'徐州市': [117.188, 34.2715],
	'扬州市': [119.427, 32.4085],
	'无锡市': [120.305, 31.5700],
	'泰州市': [119.919, 32.4760],
	'淮安市': [119.030, 33.6065],
	'盐城市': [120.148, 33.3798],
	'苏州市': [120.619, 31.3179],
	'连云港市': [119.173, 34.6015],
	'镇江市': [119.455, 32.2044],
	'上饶市': [117.955, 28.4576],
	'九江市': [115.999, 29.7196],
	'南昌市': [115.893, 28.6895],
	'吉安市': [114.992, 27.1138],
	'宜春市': [114.400, 27.8111],
	'抚州市': [116.360, 27.9545],
	'新余市': [114.947, 27.8223],
	'景德镇市': [117.186, 29.3035],
	'萍乡市': [113.859, 27.6395],
	'赣州市': [114.935, 25.8452],
	'鹰潭市': [117.035, 28.2413],
	'保定市': [115.494, 38.8865],
	'唐山市': [118.183, 39.6505],
	'廊坊市': [116.703, 39.5186],
	'张家口市': [114.893, 40.8111],
	'承德市': [117.933, 40.9925],
	'衡水市': [115.686, 37.7469],
	'邢台市': [114.520, 37.0695],
	'沧州市': [116.863, 38.2976],
	'石家庄市': [114.522, 38.0489],
	'秦皇岛市': [119.604, 39.9454],
	'邯郸市': [114.482, 36.6093],
	'三门峡市': [111.181, 34.7833],
	'信阳市': [114.085, 32.1285],
	'南阳市': [112.542, 33.0114],
	'周口市': [114.654, 33.6237],
	'商丘市': [115.641, 34.4385],
	'安阳市': [114.351, 36.1102],
	'平顶山市': [113.300, 33.7453],
	'开封市': [114.351, 34.8018],
	'新乡市': [113.912, 35.3072],
	'洛阳市': [112.447, 34.6573],
	'漯河市': [114.046, 33.5762],
	'恩施土家族苗族自治州': [109.491, 30.2858],
	'武汉市': [114.316, 30.5810],
	'濮阳市': [115.026, 35.7532],
	'焦作市': [113.211, 35.2346],
	'省直辖': [113.486, 34.1571],
	'许昌市': [113.835, 34.0267],
	'郑州市': [113.649, 34.7566],
	'驻马店市': [114.049, 32.9831],
	'鹤壁市': [114.297, 35.7554],
	'丽水市': [119.929, 28.4562],
	'台州市': [121.440, 28.6682],
	'嘉兴市': [120.760, 30.7739],
	'宁波市': [121.579, 29.8852],
	'杭州市': [120.219, 30.2592],
	'温州市': [120.690, 28.0028],
	'湖州市': [120.137, 30.8779],
	'绍兴市': [120.592, 30.0023],
	'舟山市': [122.169, 30.0360],
	'衢州市': [118.875, 28.9569],
	'金华市': [119.652, 29.1028],
	'三亚市': [109.522, 18.2577],
	'三沙市': [112.350, 16.8400],
	'海口市': [110.330, 20.0220],
	'省直辖': [109.733, 19.1805],
	'十堰市': [110.801, 32.6369],
	'咸宁市': [114.300, 29.8806],
	'孝感市': [113.935, 30.9279],
	'宜昌市': [111.310, 30.7327],
	'省直辖': [112.410, 31.2093],
	'荆州市': [112.241, 30.3325],
	'荆门市': [112.217, 31.0426],
	'襄阳市': [112.250, 32.2291],
	'鄂州市': [114.895, 30.3844],
	'随州市': [113.379, 31.7178],
	'黄冈市': [114.906, 30.4461],
	'黄石市': [115.050, 30.2161],
	'娄底市': [111.996, 27.7410],
	'岳阳市': [113.146, 29.3780],
	'常德市': [111.653, 29.0121],
	'张家界市': [110.481, 29.1248],
	'怀化市': [109.986, 27.5574],
	'株洲市': [113.131, 27.8274],
	'永州市': [111.614, 26.4359],
	'湘潭市': [112.935, 27.8350],
	'湘西土家族苗族自治州': [109.745, 28.3179],
	'益阳市': [112.366, 28.5880],
	'衡阳市': [112.583, 26.8981],
	'邵阳市': [111.461, 27.2368],
	'郴州市': [113.037, 25.7822],
	'长沙市': [112.979, 28.2134],
	'无堂区划分区域': [113.557, 22.2041],
	'澳门半岛': [113.566, 22.1950],
	'澳门离岛': [113.557, 22.2041],
	'临夏回族自治州': [103.215, 35.5985],
	'兰州市': [103.823, 36.0642],
	'嘉峪关市': [98.2816, 39.8023],
	'天水市': [105.736, 34.5843],
	'定西市': [104.626, 35.5860],
	'平凉市': [106.688, 35.5501],
	'庆阳市': [107.644, 35.7268],
	'张掖市': [100.459, 38.9393],
	'武威市': [102.640, 37.9331],
	'甘南藏族自治州': [102.917, 34.9922],
	'白银市': [104.171, 36.5466],
	'酒泉市': [98.5084, 39.7414],
	'金昌市': [102.208, 38.5160],
	'陇南市': [104.934, 33.3944],
	'三明市': [117.642, 26.2708],
	'南平市': [118.181, 26.6436],
	'厦门市': [118.103, 24.4892],
	'宁德市': [119.542, 26.6565],
	'泉州市': [118.600, 24.9016],
	'漳州市': [117.676, 24.5170],
	'福州市': [119.330, 26.0471],
	'莆田市': [119.077, 25.4484],
	'龙岩市': [117.017, 25.0786],
	'山南地区': [91.7506, 29.2290],
	'拉萨市': [91.1118, 29.6625],
	'日喀则地区': [88.8914, 29.2690],
	'昌都地区': [97.1855, 31.1405],
	'林芝地区': [94.3499, 29.6669],
	'那曲地区': [92.0670, 31.4806],
	'阿里地区': [81.1076, 30.4045],
	'六盘水市': [104.852, 26.5918],
	'安顺市': [105.928, 26.2285],
	'毕节市': [105.333, 27.4085],
	'贵阳市': [106.709, 26.6299],
	'遵义市': [106.931, 27.6999],
	'铜仁市': [109.168, 27.6749],
	'黔东南苗族侗族自治州': [107.985, 26.5839],
	'黔南布依族苗族自治州': [107.523, 26.2645],
	'黔西南布依族苗族自治州': [104.900, 25.0951],
	'丹东市': [124.338, 40.1290],
	'大连市': [121.593, 38.9487],
	'抚顺市': [123.929, 41.8773],
	'朝阳市': [120.446, 41.5718],
	'本溪市': [123.778, 41.3258],
	'盘锦市': [122.073, 41.1412],
	'营口市': [122.233, 40.6686],
	'榆林市': [109.745, 38.2794],
	'沈阳市': [123.432, 41.8086],
	'葫芦岛市': [120.860, 40.7430],
	'辽阳市': [123.172, 41.2733],
	'铁岭市': [123.854, 42.2997],
	'锦州市': [121.147, 41.1308],
	'阜新市': [121.660, 42.0192],
	'鞍山市': [123.007, 41.1187],
	'重庆市': [106.530, 29.5446],
	'咸阳市': [108.707, 34.3453],
	'宝鸡市': [107.170, 34.3640],
	'延安市': [109.500, 36.6033],
	'商洛市': [109.934, 33.8739],
	'安康市': [109.038, 32.7043],
	'渭南市': [109.483, 34.5023],
	'西安市': [108.953, 34.2777],
	'铜川市': [108.968, 34.9083],
	'汉中市': [107.045, 33.0815],
	'海北藏族自治州': [100.879, 36.9606],
	'海南藏族自治州': [100.624, 36.2843],
	'果洛藏族自治州': [100.223, 34.4804],
	'海东地区': [102.085, 36.5176],
	'海西蒙古族藏族自治州': [97.3426, 37.3737],
	'玉树藏族自治州': [97.0133, 33.0062],
	'西宁市': [101.767, 36.6407],
	'黄南藏族自治州': [102.007, 35.5228],
	'九龙': [114.173, 22.3072],
	'新界': [114.146, 22.4274],
	'香港岛': [114.183, 22.2721],
	'七台河市': [131.019, 45.7750],
	'伊春市': [128.910, 47.7346],
	'佳木斯市': [130.284, 46.8137],
	'双鸭山市': [131.171, 46.6551],
	'哈尔滨市': [126.657, 45.7732],
	'大兴安岭地区': [124.196, 51.9917],
	'大庆市': [125.021, 46.5967],
	'牡丹江市': [129.608, 44.5885],
	'绥化市': [126.989, 46.6460],
	'鸡西市': [130.941, 45.3215],
	'鹤岗市': [130.292, 47.3386],
	'黑河市': [127.500, 50.2506],
	'齐齐哈尔市': [123.987, 47.3476]
}

var styleJson = [{'featureType': 'water', 'elementType': 'all', 'stylers': {'color': '#d1d1d1'}},
	{'featureType': 'land', 'elementType': 'all', 'stylers': {'color': '#f3f3f3'}},
	{'featureType': 'railway', 'elementType': 'all', 'stylers': {'visibility': 'off'}},
	{'featureType': 'highway', 'elementType': 'all', 'stylers': {'color': '#fdfdfd'}},
	{'featureType': 'highway', 'elementType': 'labels', 'stylers': {'visibility': 'off'}},
	{'featureType': 'arterial', 'elementType': 'geometry', 'stylers': {'color': '#fefefe'}},
	{'featureType': 'arterial', 'elementType': 'geometry.fill', 'stylers': {'color': '#fefefe'}},
	{'featureType': 'poi', 'elementType': 'all', 'stylers': {'visibility': 'off'}},
	{'featureType': 'green', 'elementType': 'all', 'stylers': {'visibility': 'off'}},
	{'featureType': 'subway', 'elementType': 'all', 'stylers': {'visibility': 'off'}},
	{'featureType': 'manmade', 'elementType': 'all', 'stylers': {'color': '#d1d1d1'}},
	{'featureType': 'local', 'elementType': 'all', 'stylers': {'color': '#d1d1d1'}},
	{'featureType': 'arterial', 'elementType': 'labels', 'stylers': {'visibility': 'off'}},
	{'featureType': 'boundary', 'elementType': 'all', 'stylers': {'color': '#fefefe'}},
	{'featureType': 'building', 'elementType': 'all', 'stylers': {'color': '#d1d1d1'}},
	{'featureType': 'label', 'elementType': 'labels.text.fill', 'stylers': {'color': '#999999'}}];

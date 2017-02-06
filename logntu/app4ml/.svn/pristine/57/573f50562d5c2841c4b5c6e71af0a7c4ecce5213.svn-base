<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>获取经纬度</title>
<script
	src="/res_admin/js/vendor/jquery/v_1_9_1/jquery-1.9.1.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="http://api.map.baidu.com/api?v=2.0&ak=KZCI5e2zTNf6abf1CF0GNd1i" type="text/javascript"></script> 

<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
}

#content {
	float: left;
}

.map_top {
	line-height: 40px;
	height: 40px;
	list-style: none;
	float: left;
}

.map_top li {
	text-align: right;
	font-size: 12px;
}

#key {
	height: 20px;
	line-height: 20px;
	font-size: 12px;
	width: 300px;
}

.map_top li span {
	width: 110px;
}

.iw_poi_title {
	color: #c40000;
	line-height: 25px;
	height: 25px;
}

.iw_poi_content {
	line-height: 25px;
	height: 25px;
	font-size: 12px;
}

.btn {
	text-align: center;
}

#btn_ok {
	display: inline-block;
	color: #faddde;
	border: solid 1px #980c10;
	background: #d81b21;
	width: 100px;
	height: 25px;
	margin-top: 10px;
	background: -webkit-gradient(linear, left top, left bottom, from(#ed1c24),
		to(#aa1317));
	background: -moz-linear-gradient(top, #ed1c24, #aa1317);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ed1c24',
		endColorstr='#aa1317');
}

#btn_search {
	display: inline-block;
	color: #faddde;
	border: solid 1px #980c10;
	background: #d81b21;
	width: 50px;
	height: 20px;
	background: -webkit-gradient(linear, left top, left bottom, from(#ed1c24),
		to(#aa1317));
	background: -moz-linear-gradient(top, #ed1c24, #aa1317);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ed1c24',
		endColorstr='#aa1317');
}
</style>
</head>
<body>
	<div id="content">
		<input type="hidden" name="lat" id="lat" /> <input type="hidden"
			name="lng" id="lng" /> <input type="hidden" id="lan_lng" />
		<h4 style="color: #c40000; font-size: 13px;">点击地图获取经纬度</h4>
		<ul class="map_top">
			<li style="color: #c40000;">地址：<input type="text" id="key"
				value="${addr}" /> <input type="button"
				id="btn_search" value="搜索" /></li>
		</ul>
		<div id="content_map" style="width: 630px; height: 477px;"></div>
	</div>
</body>
</html>
<script type="text/javascript">
	var map = new BMap.Map("content_map");// 创建Map实例
	map.centerAndZoom("${addr}"); // 初始化地图,设置中心点坐标和地图级别
	//启用滚轮放大缩小
	map.enableScrollWheelZoom();
	//禁用双击放大
	map.disableDoubleClickZoom();
	//添加控件
	map.addControl(new BMap.NavigationControl());
	// var opts = { isOpen: true };
	//  map.addControl(new BMap.OverviewMapControl(opts));
	//鼠标样式
	map.setDefaultCursor("default");
	map.addEventListener(
					"click",
					function(e) { //单击地图，形成折线覆盖物
						$("#lan_lng").val(e.point.lng + "," + e.point.lat);
						$("#lng").val(e.point.lng);
						$("#lat").val(e.point.lat);
						var json = {
							title : "获取经纬度",
							icon : {
								w : 21,
								h : 21,
								l : 0,
								t : 0,
								x : 6,
								lb : 5
							}
						}
						var iconImg = createIcon(json.icon);
						var marker = new BMap.Marker(e.point, {
							icon : iconImg
						});
						var iw = new BMap.InfoWindow(
								"<b class='iw_poi_title'>"
										+ json.title
										+ "</b><div class='iw_poi_content'> 当前经纬度："
										+ e.point.lng
										+ ","
										+ e.point.lat
										+ "</div><div class='btn'><input type='button' onclick='setStoreValue()' id='btn_ok' value='获取当前坐标'></div>",
								{
									"enableMessage" : false
								});
						var label = new BMap.Label("", {
							"offset" : new BMap.Size(json.icon.lb - json.icon.x
									+ 10, -20)
						});
						label.setStyle({
							borderColor : "#808080",
							color : "red",
							cursor : "pointer"
						});

						marker.setLabel(label);
						map.clearOverlays();
						map.addOverlay(marker);

						iw.addEventListener("open", function() {
							marker.getLabel().hide();
						})
						iw.addEventListener("close", function() {
							marker.getLabel().show();
						})
						marker.openInfoWindow(iw);
					});

	//搜索, autoViewport: true 
	var local = new BMap.LocalSearch(map, {
		renderOptions : {
			map : map
		}
	});
	//local.search("<c:out value="${addr}"/>", {
	//	forceLocal : true
	//});
	$(function() {
		$("#btn_search").click(function() {
			local.search($("#key").val(), {
				forceLocal : true
			});
		});

	})

	function setStoreValue() {
		window.parent.setLanLng($("#lan_lng").val(), $("#lng").val(), $("#lat").val());
	}
	 //创建一个Icon
    function createIcon(json) {
        var icon = new BMap.Icon("/res_admin/images/main/zuobiao.png", new BMap.Size(json.w, json.h), { imageOffset: new BMap.Size(-json.l, -json.t), infoWindowOffset: new BMap.Size(json.lb + 5, 1), offset: new BMap.Size(json.x, json.h) })
        return icon;
    }
</script>
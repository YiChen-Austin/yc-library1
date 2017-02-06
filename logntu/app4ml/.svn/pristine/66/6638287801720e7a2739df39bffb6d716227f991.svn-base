<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=projectTitle%></title>
<link rel="icon" href="<%=path%>/images/logo/ml_ico.ico"
	type="image/x-icon" />
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<link href="<%=path%>/css/style.css" rel="stylesheet" />
<link href="<%=path%>/css/bootstrap.css" rel="stylesheet" />
</head>
<body>
	<jsp:include page="../include/top.jsp" >
		<jsp:param name="tag" value="center" />
	</jsp:include>
	<div class="w1100 mtb20 color999">
		您当前的位置：首页 > 个人中心 > 我要反馈
	</div>
	<div class="main w1100">
		<jsp:include page="../include/left.jsp" >
			<jsp:param name="tag" value="feedback" />
		</jsp:include>
		<div class="main-con fl">
			
			<form action="feedBack" id="uploadForm" method="post" enctype="multipart/form-data" onsubmit="document.getElementById('sub_btn').disabled = true;">
				<div class="feedback-con">
					<div class="feedback-text">
						<p>您遇到的问题或建议详情</p>
						<div class="text-div">
							<textarea name="feedBacktext"></textarea>
							<div class="text-tlt">
								如果您有意见建议想对我们说，都可以通过下面的方式发信息给我们。我们将非常乐意跟您畅快的交流！</div>
						</div>
					</div>
					<div class="feedback-zy">
						<span>注意:</span>
						<p>如果相关截图或者详细意见整理，请使用附件上传提交给我们。（单个附件最大1MB图片）</p>
						<div class="file">
							<input type="file" class="fileinput" name="file" onchange="document.getElementById('upfileResult1').innerHTML=this.value">
								<label class="feedback-sca">选择上传图片</label>
						</div>
						<span class="fileTips fl" id="upfileResult1"></span>
					</div>
					<!-- <button ></button> -->
					<input id="sub_btn" type="submit" value="确认提交" class="feedback-sc" />
				</div>
			</form>
		</div>
	</div>
	<jsp:include page="../include/bottom.jsp" />
</body>
<script src="<%=path%>/js/jquery-1.9.1.min.js"></script>
<script src="<%=path%>/js/validate.js"></script>
<script src="<%=path%>/js/layer.js"></script>

</html>


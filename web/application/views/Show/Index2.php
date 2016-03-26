<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="description" content="脑子飞啦！">
  <meta name="keywords" content="头脑风暴,思想狂潮">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <title>Idea-Sink</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp"/>
  <link rel="icon" type="image/png" href="/public/amaze/i/favicon.png">
  <meta name="mobile-web-app-capable" content="yes">
  <link rel="icon" sizes="192x192" href="/public/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <meta name="apple-mobile-web-app-title" content="Idea-Sink"/>
  <link rel="apple-touch-icon-precomposed" href="/public/amaze/i/app-icon72x72@2x.png">
  <meta name="msapplication-TileImage" content="/public/i/app-icon72x72@2x.png">
  <meta name="msapplication-TileColor" content="#0e90d2">
  <link rel="stylesheet" href="/public/amaze/css/amazeui.min.css">
  <link rel="stylesheet" href="/public/amaze/css/app.css">
	<!--[if (gte IE 9)|!(IE)]><!-->
	<script src="/public/amaze/js/jquery.min.js"></script>
	<!--<![endif]-->
	<!--[if lte IE 8 ]>
	<script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>
	<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
	<script src="/public/js/amazeui.ie8polyfill.min.js"></script>
	<![endif]-->
	<script src="/public/amaze/js/amazeui.min.js"></script>
	<script> var BASEURL="<?php echo Kohana::$base_url; ?>"</script>
</head>



<body>
<section id="idea-show" class="am-panel am-panel-default">
  <header class="am-panel-hd">
    <h3 class="am-panel-title"><?php echo $idea->title ?></h3>
    <button class="change" type="button" >切换</button>
  </header>
  <div class="am-panel-bd">
    <p><?php echo $idea->text ?></p>
    <div class="am-btn-group">
    	<button type="button" class="gp-set " data-type="1">GET</button>
    	<button type="button" class="gp-set " data-type="0">DROP</button>
	</div>
  </div>
</section>

<section id="idea-get" class="am-panel am-panel-default am-hide">
  <header class="am-panel-hd">
    <h3 class="am-panel-title">添加你的想法吧！</h3>
    <button class="change" type="button" >切换</button>
  </header>
  <div class="am-panel-bd">
  <form id="idea-box" class="am-form am-form-horizontal">
  <div class="am-form-group">
    <label for="nickname" class="am-u-sm-2 am-form-label">昵称</label>
    <div class="am-u-sm-10">
    <input id="nickname" name="nickname" value="<?php echo $nickname; ?>">
    </div>
  </div>

  <div class="am-form-group">
    <label for="idea-title" class="am-u-sm-2 am-form-label">标题</label>
    <div class="am-u-sm-10">
    <input id="idea-title" name="title">
    </div>
  </div>
  <div class="am-form-group">
    <label for="idea-content">IDEA内容</label>
    <textarea id="idea-content" name="content" rows="5"></textarea>
  </div>
  </form>
  <div class="am-form-group">
    <div class="am-u-sm-10 am-u-sm-offset-2">
      <button id="submit-idea" type="submit" class="am-btn am-btn-default">提交</button>
    </div>
  </div>
  </div>
</section>

<script>
  $(".gp-set").on("click",function(){
    $.ajax({
      type:'POST',
      url:'/ajax/save_gp',
      data:"id=<?php echo $idea->idea_id ?>&gp="+$(this).data('type'),
      success:function(data){
        alert('done!');
      }
    });
  });
  $("#submit-idea").on("click",function(){
    $.ajax({
      type:'POST',
      url:'/ajax/save_idea',
      data:$("#idea-box").serialize(),
      success:function(data){
        alert('done!');
      }
    });
  });

  //
  $(".change").on("click",function(){
	  $("section").removeClass('am-hide');
	  $(this).parent().parent().addClass('am-hide');
  });

</script>

<script src="http://doc.rebiekong.com/public/js/rebiekong.js"></script>
</body>
</html>
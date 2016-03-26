<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="description" content="">
  <meta name="keywords" content="">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <title>Idea-Sink</title>
  <script src="http://cdn.bootcss.com/FrozenUI/1.3.0/lib/zepto.min.js"></script>
  <script> var BASEURL="<?php echo Kohana::$base_url; ?>"</script>
</head>
<body>
<ul>
 <li><?php echo $idea->title ?></li>
 <li><?php echo $idea->text ?></li>
 <li><button class="gp-set" data-type="1">GET</button><button class="gp-set" data-type="0">DROP</button></li>
</ul>
<ht>
<form id="idea-box">
<table>
<tr><td>Nickname</td><td><input name="nickname" value="<?php echo $nickname; ?>"></td></tr>
<tr><td>标题</td><td><input name="title"></td></tr>
<tr><td>IDEA简述</td><td><input name="content"></td></tr>
</table>
</form>
<button id="submit-idea">SUBMIT</button>
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


</script>

<script src="http://doc.rebiekong.com/public/js/rebiekong.js"></script>
</body>
</html>

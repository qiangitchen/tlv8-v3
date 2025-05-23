<!DOCTYPE html>
<html style="height: 100%">
   <head>
       <meta charset="utf-8">
       <title>echarts</title>
       <script type="text/javascript" src="/comon/js/jquery/jquery.min.js"></script>
       <script type="text/javascript" src="/resources/echarts/js/echarts.min.js"></script>
       <script type="text/javascript" src="/resources/echarts/js/echarts-gl.min.js"></script>
       <script type="text/javascript" src="/resources/echarts/js/ecStat.min.js"></script>
       <script type="text/javascript" src="/resources/echarts/js/dataTool.min.js"></script>
       <script type="text/javascript" src="/resources/echarts/js/china.js"></script>
       <script type="text/javascript" src="/resources/echarts/js/world.js"></script>
       <script type="text/javascript" src="//api.map.baidu.com/api?v=2.0&ak=S8xLfAZ3Exim2biZUXwv9AvDIXwQ5CzR"></script>
       <script type="text/javascript" src="/resources/echarts/js/bmap.min.js"></script>
	   <script type="text/javascript" src="/resources/echarts/js/dark.js"></script>
	   <script type="text/javascript" src="/comon/js/json2.js"></script>
   </head>
   <body style="height: 100%; margin: 0; padding:0px;" onload="initCharts()">
       <div id="container" style="width:100%; height: 100%; overflow:hidden;"></div>
       <script type="text/javascript">
		var dom = document.getElementById("container");
		var myChart = echarts.init(dom, "{{theme}}");
		var app = {};
		option = null;
		{{javascripts}}
		function initCharts(){
			options = {{chartoptions}}; 
			try{
				if(typeof options == "string"){
					options = JSON.parse(options);
					parseChildObject(options);
					try{
						//有的图形不需要X轴信息
						options.xAxis.data = JSON.childObject(options.xAxis.data);
					}catch (e) {
					}
				}
				try{
					var datas = options.series[0].data;
					for(var i=0; i< datas.length; i++){
						datas[i] = window.eval("("+datas[i]+")");
					}
					options.series[0].data = JSON.childObject(datas);
				}catch(e){
				}
				myChart.setOption(options, true);
			}catch (e) {
				try{
					myChart.setOption(window.eval("("+options+")"), true);
				}catch (e1) {
					if(option){
						myChart.setOption(option);
					}
				}
			}
		}
		function parseChildObject(pjson){
			for(var jk in pjson){
				pjson[jk] = JSON.childObject(pjson[jk]);
			}
		}
       </script>
   </body>
</html>
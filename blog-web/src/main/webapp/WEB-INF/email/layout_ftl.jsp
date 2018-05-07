<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE>

<html>
  <head>
   <title><tiles:getAsString name="title" /></title>
 </head>
  <body>
	<tiles:insertAttribute name="content"/>
  </body>
</html>
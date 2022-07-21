<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/birt.tld" prefix="birt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<HEAD>
		<TITLE>Eclipse BIRT Home</TITLE>
	</HEAD>
	<BODY>
		<birt:parameterPage
		id="report1" 
		name="page1"
		reportDesign="bp_riskmatrix_standalone.rptdesign"
		isCustom="true"
		pattern="frameset">

		Cascading Parameter1  This parameter is hidden: <birt:paramDef id="5" name="Country" value="USA" cssClass="class1"/>		
		<br><br>		
		Cascading Parameter2: <birt:paramDef id="6" name="City"/>
		<br><br>		
		Cascading Parameter3: <birt:paramDef id="7" name="Customer"/>
		<br><br>			
		<input type="submit" name="submit" value="Sumbit form"/>
		<br><br>
	</birt:parameterPage>
	</BODY>
</HTML>
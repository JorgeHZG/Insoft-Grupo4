FROM bitnami/tomcat:10.1.12
MAINTAINER <alumnos>

ADD *.jar /opt/bitnami/tomcat/lib/
ADD context.xml /opt/bitnami/tomcat/conf/
ADD server.xml /opt/bitnami/tomcat/conf/

ADD ROOT.war /opt/bitnami/tomcat/webapps/
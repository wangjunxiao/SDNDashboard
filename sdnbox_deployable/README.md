#Prerequisites

1. you need to install a db server such as mysql server, provides db store service for sdnbox.  
2. underlying openflow network need to consist of ofcontroller, ofswitch and host. you can choose floodlight or ryu this like open source ofcontroller to do this work. as for ofswitch and host, you can simply choose mininet to substitute for them, of course, can also use physical ofsiwitch and host.
3. you need to install jdk1.7 32bit version.

#Configuration

1. configure db store service, edit sdnbox_deployable/apache-tomcat-6.0.44-green/webapps/sdnbox/WEB-INF/classes/config.properties as below. here, we use the example of mysql server.

        masterdb.url=jdbc\:mysql\://{server-ip}\:{server-port}/{schema-name}?noAccessToProcedureBodies\=true

2. configure underlying openflow network. here, we use the example of floodlight, ryu and mininet.

        $cd floodlight_0.9
        $ant
        $cd target
        $java -jar floodlight.jar
        
        $cd ryu/ryu/app
        $ryu-manager --verbose --observe-links simple_switch.py ofctl_rest.py rest_topology.py rest_firewall.py
        
        $mn -c
        $mn --controller remote,ip={ofcontroller-ip},port={ofport} --topo linear,2

3. configure northbound interface, edit sdnbox_deployable/apache-tomcat-6.0.44-green/webapps/sdnbox/WEB-INF/classes/constants.properties as below. here, we use the example of floodlight and ryu.

        flood_preurl=http\://{floodlight1-ip}\:{floodlight1-restport};http\://{floodlight2-ip}\:{floodlight2-restport}
        Ryu_preurl=http\://{ryu1-ip}\:{ryu1-restport};http\://{ryu2-ip}\:{ryu2-restport}

#Startup
        
        sdnbox_deployable/apache-tomcat-6.0.44-green/bin/startup.bat or startup.sh

#Credits

1. apache tomcat 2. blazeds

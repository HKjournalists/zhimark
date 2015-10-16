#!/usr/bin/expect
set timeout 600
spawn scp ./classes/artifacts/gateway/gateway.war root@121.40.113.130:/var/www/o2o-backstage
expect { 
        "passphrase"
        { 
            send "Llqwql41430\n"; 
        } 
        "password"
        { 
              send "Llqwql41430\n"; 
        } 
        "yes/no"
        { 
              send "yes\n"; 
              exp_continue; 
        } 
} 
expect eof
set timeout 60
spawn ssh root@121.40.113.130
expect {
	"passphrase"
        {
            send "Llqwql41430\n";
        }
        "password"
        {
              send "Llqwql41430\n";
        }
        "yes/no"
        {
              send "yes\n";
              exp_continue;
        }
}
expect "#"
send "kill -9 \$(ps -ef | grep tomcat | grep -v grep | awk '{print \$2}')\r";
expect "#";
send "rm -rf /etc/tomcat/apache-tomcat-7.0.61/webapps/gateway\n";
expect "#";
send "startup.sh\n";
expect eof

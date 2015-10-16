#!/usr/bin/expect
set timeout 600
spawn scp ./classes/artifacts/gateway/gateway.war root@121.43.192.121:/etc/tomcat/apache-tomcat-7.0.64/webapps/
expect { 
        "passphrase"
        { 
            send "1QAZSE4zmk\n"; 
        } 
        "password"
        { 
              send "1QAZSE4zmk\n"; 
        } 
        "yes/no"
        { 
              send "yes\n"; 
              exp_continue; 
        } 
} 
expect eof
set timeout 60
spawn ssh root@121.43.192.121
expect {
	"passphrase"
        {
            send "1QAZSE4zmk\n";
        }
        "password"
        {
              send "1QAZSE4zmk\n";
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
send "rm -rf /etc/tomcat/apache-tomcat-7.0.64/webapps/gateway\n";
expect "#";
send "startup.sh\n";
expect eof

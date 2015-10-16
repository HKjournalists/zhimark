#!/usr/bin/expect
set timeout 600
spawn scp ./classes/artifacts/gateway/gateway.war root@121.40.220.224:/usr/lib/jvm/apache-tomcat-7.0.64/webapps/
expect { 
        "passphrase"
        { 
            send "Bamsion123\n"; 
        } 
        "password"
        { 
              send "Bamsion123\n"; 
        } 
        "yes/no"
        { 
              send "yes\n"; 
              exp_continue; 
        } 
} 
expect eof
set timeout 60
spawn ssh root@121.40.220.224
expect {
	"passphrase"
        {
            send "Bamsion123\n";
        }
        "password"
        {
              send "Bamsion123\n";
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
send "rm -rf /usr/lib/jvm/apache-tomcat-8.0.24/webapps/gateway\n";
expect "#";
send "startup.sh\n";
expect eof

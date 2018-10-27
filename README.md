# ZEROMQ-CHAT-APPLICATION

1) mvn package and then mvn install


2) Running the RMI 

    a) mvn exec:java -Djava.rmi.server.useCodebaseOnly=false -Djava.security.policy=policy -Dexec.mainClass=edu.gvsu.cis.MyPresenceServer


3) Running the clients 

To run a client with name “tom”, I enter:

    a) mvn exec:java -Djava.rmi.server.useCodebaseOnly=false -Djava.security.policy=policy -Dexec.mainClass=edu.gvsu.cis.ChatClient -   
       Dexec.args=tom


    b)mvn exec:java -Djava.rmi.server.useCodebaseOnly=false -Djava.security.policy=policy -Dexec.mainClass=edu.gvsu.cis.ChatClient -             Dexec.args=bob

    c)mvn exec:java -Djava.rmi.server.useCodebaseOnly=false -Djava.security.policy=policy -Dexec.mainClass=edu.gvsu.cis.ChatClient -     
      Dexec.args=alice



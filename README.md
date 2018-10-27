# ZEROMQ-CHAT-APPLICATION


1) mvn package


2) Running the RMI 
mvn exec:java -Djava.rmi.server.useCodebaseOnly=false -Djava.security.policy=policy -Dexec.mainClass=edu.gvsu.cis.MyPresenceServer


3) Running the clients 
To run a client with name “bob”, I enter:

mvn exec:java -Djava.rmi.server.useCodebaseOnly=false -Djava.security.policy=policy -Dexec.mainClass=edu.gvsu.cis.ChatClient -Dexec.args=tom


mvn exec:java -Djava.rmi.server.useCodebaseOnly=false -Djava.security.policy=policy -Dexec.mainClass=edu.gvsu.cis.ChatClient -Dexec.args=bob

mvn exec:java -Djava.rmi.server.useCodebaseOnly=false -Djava.security.policy=policy -Dexec.mainClass=edu.gvsu.cis.ChatClient -Dexec.args=alice



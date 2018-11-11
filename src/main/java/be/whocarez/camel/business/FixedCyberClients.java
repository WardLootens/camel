package be.whocarez.camel.business;

public class FixedCyberClients implements CyberClients {

    public String queueNameByGuid(String guid) {
        return "q-" + guid;
    }

}

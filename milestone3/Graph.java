package test;

import java.util.ArrayList;
import java.util.HashMap;

import test.TopicManagerSingleton.TopicManager;

public class Graph extends ArrayList<Node> {

    public boolean hasCycles() {
        for (Node node : this) {
            if (node.hasCycles()) {
                return true;
            }
        }
        return false;
    }

    public void createFromTopics() {
        this.clear();
        TopicManager tm = TopicManagerSingleton.get();
        HashMap<String, Node> nodeMap = new HashMap<>();

        // Create nodes for Topics and Agents
        for (Topic topic : tm.getTopics()) {
            String topicNodeName = "T" + topic.name;
            Node topicNode = nodeMap.computeIfAbsent(topicNodeName, Node::new);

            // Topic -> Subscribers (Agents)
            for (Agent sub : topic.getSubs()) {
                String agentNodeName = "A" + sub.getName();
                Node agentNode = nodeMap.computeIfAbsent(agentNodeName, Node::new);
                topicNode.addEdge(agentNode);
            }

            // Publishers (Agents) -> Topic
            for (Agent pub : topic.getPubs()) {
                String agentNodeName = "A" + pub.getName();
                Node agentNode = nodeMap.computeIfAbsent(agentNodeName, Node::new);
                agentNode.addEdge(topicNode);
            }
        }

        this.addAll(nodeMap.values());
    }
}

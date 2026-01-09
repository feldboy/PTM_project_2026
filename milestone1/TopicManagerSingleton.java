package test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TopicManagerSingleton {

    public static class TopicManager{
        private static final TopicManager instance = new TopicManager();
        private Map<String, Topic> topics;
        
        // Private constructor
        private TopicManager() {
            this.topics = new HashMap<>();
        }
        
        // Get topic by name, create if doesn't exist (flyweight pattern)
        public Topic getTopic(String name) {
            if (!topics.containsKey(name)) {
                topics.put(name, new Topic(name));
            }
            return topics.get(name);
        }
        
        // Get all topics
        public Collection<Topic> getTopics() {
            return topics.values();
        }
        
        // Clear all topics
        public void clear() {
            topics.clear();
        }
    }
    
    // Get singleton instance
    public static TopicManager get() {
        return TopicManager.instance;
    }
}

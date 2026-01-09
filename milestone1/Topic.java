package test;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    public final String name;
    private List<Agent> subs;  // subscribers
    private List<Agent> pubs;  // publishers
    
    // Package-private constructor
    Topic(String name){
        this.name = name;
        this.subs = new ArrayList<>();
        this.pubs = new ArrayList<>();
    }

    public void subscribe(Agent a){
        if (!subs.contains(a)) {
            subs.add(a);
        }
    }
    
    public void unsubscribe(Agent a){
        subs.remove(a);
    }

    public void publish(Message m){
        // Call callback on all subscribers
        for (Agent agent : subs) {
            agent.callback(this.name, m);
        }
    }

    public void addPublisher(Agent a){
        if (!pubs.contains(a)) {
            pubs.add(a);
        }
    }

    public void removePublisher(Agent a){
        pubs.remove(a);
    }
}

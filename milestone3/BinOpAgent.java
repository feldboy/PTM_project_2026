package test;

import java.util.function.BinaryOperator;

import test.TopicManagerSingleton.TopicManager;

public class BinOpAgent implements Agent {
    private final String name;
    private final String topic1;
    private final String topic2;
    private final String outputTopic;
    private final BinaryOperator<Double> op;
    private Double val1 = null;
    private Double val2 = null;

    public BinOpAgent(String name, String topic1, String topic2, String outputTopic, BinaryOperator<Double> op) {
        this.name = name;
        this.topic1 = topic1;
        this.topic2 = topic2;
        this.outputTopic = outputTopic;
        this.op = op;

        TopicManager tm = TopicManagerSingleton.get();
        tm.getTopic(topic1).subscribe(this);
        tm.getTopic(topic2).subscribe(this);
        tm.getTopic(outputTopic).addPublisher(this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void reset() {
        val1 = 0.0;
        val2 = 0.0;
    }

    @Override
    public void callback(String topic, Message msg) {
        if (topic.equals(topic1)) {
            val1 = msg.asDouble;
        } else if (topic.equals(topic2)) {
            val2 = msg.asDouble;
        }

        if (val1 != null && val2 != null) {
            Double result = op.apply(val1, val2);
            TopicManagerSingleton.get().getTopic(outputTopic).publish(new Message(result));
        }
    }

    @Override
    public void close() {
    }
}

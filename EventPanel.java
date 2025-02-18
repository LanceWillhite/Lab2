import javax.swing.*;
import java.awt.*;

public class EventPanel extends JPanel {
    private Event event;
    private JButton completeButton;

    public EventPanel(Event event) {
        this.event = event;
        setLayout(new GridLayout(2, 2));

        JLabel nameLabel = new JLabel(event.getName());
        JLabel dateLabel = new JLabel("Date: " + event.getDateTime().toString());

        add(nameLabel);
        add(dateLabel);

        if (event instanceof Completable) {
            completeButton = new JButton("Complete");
            completeButton.addActionListener(e -> {
                ((Completable) event).complete();
                completeButton.setEnabled(false);
            });
            add(completeButton);
        }
    }
}

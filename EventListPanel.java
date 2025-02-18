import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class EventListPanel extends JPanel {
    private ArrayList<Event> events;
    private JPanel displayPanel;
    private JButton addEventButton;

    public EventListPanel() {
        events = new ArrayList<>();
        setLayout(new BorderLayout());

        // Display panel for events
        displayPanel = new JPanel();
        displayPanel.setLayout(new GridLayout(0, 1));

        // Scrollable panel for events
        JScrollPane scrollPane = new JScrollPane(displayPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Add event button
        addEventButton = new JButton("Add Event");
        addEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddEventModal(EventListPanel.this); // Open AddEventModal
            }
        });

        // Control panel (for buttons)
        JPanel controlPanel = new JPanel();
        controlPanel.add(addEventButton);
        add(controlPanel, BorderLayout.SOUTH);

        addDefaultEvents();
    }

    public void addEvent(Event event) {
        events.add(event);
        refreshDisplay();
    }

    private void refreshDisplay() {
        displayPanel.removeAll();
        for (Event e : events) {
            displayPanel.add(new EventPanel(e));
        }
        displayPanel.revalidate();
        displayPanel.repaint();
    }

    private void addDefaultEvents() {
        events.add(new Deadline("Project Due", LocalDateTime.now().plusDays(5)));
        events.add(new Meeting("Team Meeting", LocalDateTime.now(), LocalDateTime.now().plusHours(1), "Zoom"));
        refreshDisplay();
    }
}

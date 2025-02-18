import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddEventModal extends JDialog {
    private JTextField nameField;
    private JTextField dateTimeField;
    private JComboBox<String> eventTypeDropdown;
    private JTextField endDateTimeField;
    private JTextField locationField;
    private JButton addButton;
    private EventListPanel eventListPanel;

    public AddEventModal(EventListPanel eventListPanel) {
        this.eventListPanel = eventListPanel;
        setTitle("Add Event");
        setSize(400, 300);
        setLayout(new GridLayout(6, 2));

        // Event name
        add(new JLabel("Event Name:"));
        nameField = new JTextField();
        add(nameField);

        // Event type dropdown (Deadline or Meeting)
        add(new JLabel("Event Type:"));
        eventTypeDropdown = new JComboBox<>(new String[]{"Deadline", "Meeting"});
        add(eventTypeDropdown);

        // DateTime (format: yyyy-MM-dd HH:mm)
        add(new JLabel("Start DateTime (yyyy-MM-dd HH:mm):"));
        dateTimeField = new JTextField();
        add(dateTimeField);

        // End DateTime (only for Meeting)
        add(new JLabel("End DateTime (yyyy-MM-dd HH:mm, for Meeting only):"));
        endDateTimeField = new JTextField();
        add(endDateTimeField);

        // Location (only for Meeting)
        add(new JLabel("Location (for Meeting only):"));
        locationField = new JTextField();
        add(locationField);

        // Add button
        addButton = new JButton("Add Event");
        add(addButton);

        // Button event handler
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEvent();
            }
        });

        setModal(true);
        setVisible(true);
    }

    private void addEvent() {
        try {
            String name = nameField.getText();
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

            String selectedType = (String) eventTypeDropdown.getSelectedItem();
            Event newEvent;

            if ("Deadline".equals(selectedType)) {
                newEvent = new Deadline(name, dateTime);
            } else {
                LocalDateTime endDateTime = LocalDateTime.parse(endDateTimeField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                String location = locationField.getText();
                newEvent = new Meeting(name, dateTime, endDateTime, location);
            }

            eventListPanel.addEvent(newEvent);
            dispose(); // Close modal after adding
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input! Please check your data format.");
        }
    }
}

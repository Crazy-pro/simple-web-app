package alex.klimchuk.mastery.java.task.jms;

import org.springframework.beans.factory.annotation.Autowired;
import alex.klimchuk.mastery.java.task.entities.TransferableEntity;
import alex.klimchuk.mastery.java.task.services.EmployeeService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * Copyright Alex Klimchuk (c) 29.02.2020.
 */
@Component
public class MessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageListener.class);

    @Autowired
    private EmployeeService employeeService;

    @JmsListener(destination = "MessageQueue")
    public void receiveMessage(final TransferableEntity entity) {
        LOGGER.debug("Received message: \n" + entity);
        employeeService.updateJobTitleByDepartmentId(entity.getDepartmentId(), entity.getJobTitle());
    }

}
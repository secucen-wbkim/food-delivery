package fooddelivery.infra;

import fooddelivery.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class NotificationLogHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<NotificationLog>> {

    @Override
    public EntityModel<NotificationLog> process(
        EntityModel<NotificationLog> model
    ) {
        return model;
    }
}

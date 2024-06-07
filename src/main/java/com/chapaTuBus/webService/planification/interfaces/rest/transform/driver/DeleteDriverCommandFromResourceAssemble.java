package com.chapaTuBus.webService.planification.interfaces.rest.transform.driver;

import com.chapaTuBus.webService.planification.domain.model.commands.driver.DeleteDriverCommand;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.driver.SoftDeleteDriverResource;

public class DeleteDriverCommandFromResourceAssemble {

    public static DeleteDriverCommand toCommand (SoftDeleteDriverResource resource){
        return new DeleteDriverCommand(resource.driverId());
    }
}

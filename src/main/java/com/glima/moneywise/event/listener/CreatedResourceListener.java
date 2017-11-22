package com.glima.moneywise.event.listener;

import com.glima.moneywise.event.CreatedResourceEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

/**
 * Created by Guilherme on 22/11/2017.
 */
@Component
public class CreatedResourceListener implements ApplicationListener<CreatedResourceEvent> {

    @Override
    public void onApplicationEvent(CreatedResourceEvent createdResourceEvent) {
        HttpServletResponse response = createdResourceEvent.getResponse();
        Long id = createdResourceEvent.getId();
        addLocationHeader(response, id);
    }

    private void addLocationHeader(HttpServletResponse response, Long id) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(id).toUri();
        response.addHeader("Location", uri.toASCIIString());
    }

}

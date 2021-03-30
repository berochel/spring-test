package io.github.mat3e.controller;

import io.github.mat3e.model.TaskConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class InfoController {

    public InfoController(DataSourceProperties dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    private DataSourceProperties dataSource;

    @Value("${task.allowMultipleTasksFromTemplate}")
    private boolean myProp;

    @GetMapping("/info/url")
    String url()
    {
        return dataSource.getUrl();
    }

    @GetMapping("/info/prop")
    boolean myProp(){
        return myProp;
    }
}

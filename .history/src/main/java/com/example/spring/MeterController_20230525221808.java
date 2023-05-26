package com.example.spring;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
//import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MeterController {
  @QueryMapping
  public Meter getMeterById(@Argument String id) {
    return Meter.getById(id);
  }

  @QueryMapping
  public Meter getAllMeters() {
    return Meter.getAll();
  }

  /*@SchemaMapping
  public Author author(Meter meter) {
      return Author.getById(meter.authorId());
  }*/
}
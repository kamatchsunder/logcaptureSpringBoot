package com.sunder.logcapture.log;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@ToString(of = "log")
public class LogModel {

    private int log;

    private String name;

}

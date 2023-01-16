package com.EnocaIsYeriChall.EnocaIsYeriChall.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Response {
    private String statusCode;
    private String statusMsg;

}
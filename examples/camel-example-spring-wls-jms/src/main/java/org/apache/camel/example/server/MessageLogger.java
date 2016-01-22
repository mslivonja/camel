/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.example.server;

import org.apache.camel.Body;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * This is the implementation of the business service.
 */
// START SNIPPET: e1
@Service(value = "handler")
public class MessageLogger implements MessageHandler {

    private static final Logger log = Logger.getLogger(MessageLogger.class);

    public void handleMessage(@Body String msg) {
        log.info("Received message: '" + msg + "'");
    }
}
// END SNIPPET: e1

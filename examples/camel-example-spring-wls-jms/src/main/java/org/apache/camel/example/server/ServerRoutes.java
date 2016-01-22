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

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.example.util.FileConsumerBean;

/**
 * This class defines the routes on the Server. The class extends a base class in Camel {@link RouteBuilder}
 * that can be used to easily setup the routes in the configure() method.
 */
// START SNIPPET: e1
public class ServerRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // route from the numbers queue to our business that is a spring bean registered with the id=multiplier
        // Camel will introspect the multiplier bean and find the best candidate of the method to invoke.
        // You can add annotations etc to help Camel find the method to invoke.
        // As our multiplier bean only have one method its easy for Camel to find the method to use.
        from("wls:queue:testQueue").to("handler");

        from("wls:queue:jms.test.proxy.request.queue")
                .transform().simple("Response: '${body}'")
                .log("Forwarding message ${header.breadCrumbId} to response queue...")
                .to("wls:queue:jms.test.proxy.response.queue");

        from("wls:queue:jms.test.request.queue")
                .bean("fileConsumer")
                .to("wls:queue:jms.test.response.queue");
    }
}
// END SNIPPET: e1

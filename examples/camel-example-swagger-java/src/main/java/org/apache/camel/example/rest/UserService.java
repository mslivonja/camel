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
package org.apache.camel.example.rest;

import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * A {@link org.apache.camel.example.rest.User} service which we rest enable the routes defined in the XML file.
 */
public class UserService {

    private static final Logger log = Logger.getLogger(UserService.class);

    // use a tree map so they become sorted
    private final Map<String, User> users = new TreeMap<String, User>();

    private Random ran = new Random();

    public UserService() {
        users.put("123", new User(123, "John Doe"));
        users.put("456", new User(456, "Donald Duck"));
        users.put("789", new User(789, "Slow Turtle"));
        users.put("789", new User(9999, "Matija Slivonja", "Sokolgradska 25a", "Zagreb"));
    }

    /**
     * Gets a user by the given id
     *
     * @param id  the id of the user
     * @return the user, or <tt>null</tt> if no user exists
     */
    public User getUser(String id) {
        log.debug("GET User with ID['" + id + "']");
        if ("789".equals(id)) {
            // simulate some cpu processing time when returning the slow turtle
            int delay = 500 + ran.nextInt(1500);
            try {
                Thread.sleep(delay);
            } catch (Exception e) {
                // ignore
            }
        }
        return users.get(id);
    }

    /**
     * List all users
     *
     * @return the list of all users
     */
    public Collection<User> listUsers() {
        log.debug("GET All Users");
        return users.values();
    }

    /**
     * Updates or creates the given user
     *
     * @param user the user
     */
    public void updateUser(User user) {
        log.debug("Create NEW User with ID['" + user.getId() + "']");
        users.put("" + user.getId(), user);
    }
}

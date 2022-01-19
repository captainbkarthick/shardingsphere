/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.example.generator;

import java.util.HashMap;
import java.util.Map;

/**
 * Example template factory.
 */
public final class ExampleTemplateFactory {
    
    private static final String FRAMEWORK_KEY = "framework";
    
    private static final String FEATURE_KEY = "feature";
    
    /**
     * Get template resources that need to be renamed.
     * 
     * @param dataModel data model
     * @return rename template map
     */
    public static Map<String, String> getRenameTemplate(final Map<String, String> dataModel) {
        Map<String, String> result = new HashMap<>(3, 1);
        result.put("Example", "java/Example.ftl");
        result.put("ExampleService", "java/ExampleService.ftl");
        if (FrameworkType.JDBC.getFramework().equals(dataModel.get(FRAMEWORK_KEY))) {
            result.put("Configuration", "java/config/configuration.ftl");
        }
        return result;
    }
    
    /**
     * Get template resources that do not need to be renamed.
     * 
     * @param dataModel data model
     * @return not need rename template map
     */
    public static Map<String, String> getUnReNameTemplate(final Map<String, String> dataModel) {
        Map<String, String> result = new HashMap<>(7, 1);
        if (dataModel.getOrDefault(FEATURE_KEY, "").contains(FeatureType.ENCRYPT.getFeature())) {
            result.put("java/TestQueryAssistedShardingEncryptAlgorithm", "TestQueryAssistedShardingEncryptAlgorithm.java");
        }
        result.put("java/entity/Order", "entity/Order.java");
        result.put("java/entity/OrderItem", "entity/OrderItem.java");
        result.put("java/entity/Address", "entity/Address.java");
        switch (dataModel.get(FRAMEWORK_KEY)) {
            case "jdbc":
                result.put("java/repository/jdbc/OrderItemRepository", "repository/OrderItemRepository.java");
                result.put("java/repository/jdbc/OrderRepository", "repository/OrderRepository.java");
                result.put("java/repository/jdbc/AddressRepository", "repository/AddressRepository.java");
                break;
            case "jpa":
                result.put("java/repository/jpa/OrderItemRepository", "repository/OrderItemRepository.java");
                result.put("java/repository/jpa/OrderRepository", "repository/OrderRepository.java");
                result.put("java/repository/jpa/AddressRepository", "repository/AddressRepository.java");
                break;
            case "mybatis":
                result.put("java/repository/mybatis/OrderItemRepository", "repository/OrderItemRepository.java");
                result.put("java/repository/mybatis/OrderRepository", "repository/OrderRepository.java");
                result.put("java/repository/mybatis/AddressRepository", "repository/AddressRepository.java");
                break;
            default:
                break;
        }
        return result;
    }
    
    /**
     * Get template resources map.
     * 
     * @param dataModel data model
     * @return resource map
     */
    public static Map<String, String> getResourceTemplate(final Map<String, String> dataModel) {
        Map<String, String> result = new HashMap<>(6, 1);
        if (dataModel.getOrDefault(FEATURE_KEY, "").contains(FeatureType.ENCRYPT.getFeature())) {
            result.put("resources/spi/encryptAlgorithm", "META-INF/services/org.apache.shardingsphere.encrypt.spi.EncryptAlgorithm");
        }
        switch (dataModel.get(FRAMEWORK_KEY)) {
            case "spring-boot-starter":
                result.put("resources/properties/application", "application.properties");
                break;
            case "spring-namespace":
                result.put("resources/xml/application", "application.xml");
                break;
            case "mybatis":
                result.put("resources/mappers/OrderItemMapper", "mappers/OrderItemMapper.xml");
                result.put("resources/mappers/OrderMapper", "mappers/OrderMapper.xml");
                result.put("resources/mappers/AddressMapper", "mappers/AddressMapper.xml");
                break;
            default:
                break;
        }
        result.put("resources/logback", "logback.xml");
        return result;
    }
}
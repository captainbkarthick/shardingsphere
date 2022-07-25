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

package org.apache.shardingsphere.encrypt.rewrite.token.generator;

import org.apache.shardingsphere.encrypt.rule.EncryptRule;
import org.apache.shardingsphere.infra.binder.statement.dml.SelectStatementContext;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public final class InsertCipherNameTokenGeneratorTest extends EncryptGeneratorBaseTest {
    
    private InsertCipherNameTokenGenerator generator;
    
    @Before
    public void setup() {
        generator = new InsertCipherNameTokenGenerator();
        generator.setEncryptRule(new EncryptRule(createEncryptRuleConfiguration()));
    }
    
    @Test
    public void assertIsNotGenerateSQLTokenWithNotInsertStatement() {
        assertFalse(generator.isGenerateSQLToken(mock(SelectStatementContext.class)));
    }
    
    @Test
    public void assertIsGenerateSQLTokenWithInsertStatementContext() {
        assertTrue(generator.isGenerateSQLToken(createInsertStatementContext(Collections.emptyList())));
    }
    
    @Test
    public void assertGenerateSQLTokensWithInsertStatementContext() {
        Collection<?> tokens = generator.generateSQLTokens(createInsertStatementContext(Collections.emptyList()));
        assertThat(tokens.size(), is(1));
    }
}
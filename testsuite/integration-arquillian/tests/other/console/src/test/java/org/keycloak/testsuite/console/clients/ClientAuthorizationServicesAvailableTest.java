/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.keycloak.testsuite.console.clients;

import org.jboss.arquillian.graphene.page.Page;
import org.junit.Test;
import org.keycloak.common.Profile;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.testsuite.ProfileAssume;
import org.keycloak.testsuite.console.page.clients.settings.ClientSettings;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.keycloak.testsuite.auth.page.login.Login.OIDC;

/**
 *
 * @author <a href="mailto:vramik@redhat.com">Vlastislav Ramik</a>
 */
public class ClientAuthorizationServicesAvailableTest extends AbstractClientTest {

    private ClientRepresentation newClient;

    @Page
    private ClientSettings clientSettingsPage;

    @Test
    public void authzServicesAvailable() {
        ProfileAssume.assumePreview();

        newClient = createClientRep("oidc-public", OIDC);
        createClient(newClient);
        assertEquals("oidc-public", clientSettingsPage.form().getClientId());

        assertTrue(driver.findElement(By.xpath("//*[@for='authorizationServicesEnabled']")).isDisplayed());
    }

    @Test
    public void authzServicesUnavailable() throws InterruptedException {
        ProfileAssume.assumePreviewDisabled();

        newClient = createClientRep("oidc-public", OIDC);
        createClient(newClient);
        assertEquals("oidc-public", clientSettingsPage.form().getClientId());

        assertFalse(driver.findElement(By.xpath("//*[@for='authorizationServicesEnabled']")).isDisplayed());

    }
}
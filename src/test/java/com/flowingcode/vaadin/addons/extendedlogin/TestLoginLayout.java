/*-
 * #%L
 * Extended Login Add-on
 * %%
 * Copyright (C) 2023 - 2026 Flowing Code
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.flowingcode.vaadin.addons.extendedlogin;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginI18n.Header;

/**
 * Test Login Layout that customizes titles, descriptions and additional information.
 *
 * @author mlopez
 */
public class TestLoginLayout extends LoginLayout {

  private static final long serialVersionUID = 1L;

  public TestLoginLayout() {
    setI18n(createI18N());
    Image image = new Image("/img/LogoChicoGlow.png", "Login image");
    image.setClassName("logo-image");
    image.setWidth("fit-content");
    image.setHeight("fit-content");
    replaceHeaderComponent(image);
  }

  private LoginI18n createI18N() {
    LoginI18n i18n = LoginI18n.createDefault();
    LoginI18n.Header i18nHeader = new Header();
    i18n.setHeader(i18nHeader);
    i18nHeader.setTitle("My Application");
    i18nHeader.setDescription("Test Application for extended login component");

    LoginI18n.Form i18nForm = i18n.getForm();
    i18nForm.setTitle("Change Password");
    i18n.setForm(i18nForm);

    i18n.setAdditionalInformation("Change your password");
    return i18n;
  }
}

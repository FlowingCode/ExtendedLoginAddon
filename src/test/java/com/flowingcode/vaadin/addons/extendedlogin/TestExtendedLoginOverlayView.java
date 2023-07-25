/*-
 * #%L
 * Extended Login Add-on
 * %%
 * Copyright (C) 2023 Flowing Code
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

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import java.util.Arrays;
import org.junit.Ignore;

/**
 * View that provides a demo of using an ExtendedLoginOverlay.
 *
 * @author mlopez
 */
@SuppressWarnings("serial")
@Route(value = "extended-login/login-overlay")
@CssImport("./styles/extended-login-styles.css") // hide-source
@Ignore
public class TestExtendedLoginOverlayView extends Div {

  public TestExtendedLoginOverlayView() {
    ExtendedLoginOverlay elo = new ExtendedLoginOverlay();
    elo.replaceFormComponents(
        new TextField("Email", "someone@company.com"),
        new ComboBox<String>("Branch", Arrays.asList("Santa Fe", "Rosario")),
        new PasswordField("Password"),
        new Button("Sign In", ev -> Notification.show("Login successfull")));
    Image image = new Image("/img/LogoChicoGlow.png", "Login image");
    image.setClassName("logo-image");
    image.setWidth("fit-content");
    image.setHeight("fit-content");
    elo.replaceHeaderComponent(image);
    elo.replaceForgotPassword(new Anchor("https://www.flowingcode.com", "Flowing Code Site"));
    elo.setOpened(true);
    add(elo);
  }
}

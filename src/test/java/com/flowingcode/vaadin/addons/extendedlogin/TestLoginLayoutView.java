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
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

/**
 * Test View for demonstrating how to use LoginLayout.
 *
 * @author mlopez
 */
@SuppressWarnings("serial")
@Route(value = "login-layout", layout = TestLoginLayout.class)
public class TestLoginLayoutView extends Div {

  public TestLoginLayoutView() {
    add(createChangePasswordForm());
  }

  private FormLayout createChangePasswordForm() {
    TextField username = new TextField("Username");
    username.setEnabled(false);
    PasswordField password = new PasswordField("Password");
    PasswordField confirmPassword = new PasswordField("Confirm password");
    Button accept = new Button("Accept", ev -> Notification.show("Password changed."));

    FormLayout formLayout = new FormLayout();
    formLayout.add(username, password, confirmPassword, accept);
    formLayout.setResponsiveSteps(
        // Use one column by default
        new ResponsiveStep("0", 1),
        // Use two columns, if layout's width exceeds 500px
        new ResponsiveStep("500px", 2));
    // Stretch the username field over 2 columns
    formLayout.setColspan(username, 2);
    return formLayout;
  }
}

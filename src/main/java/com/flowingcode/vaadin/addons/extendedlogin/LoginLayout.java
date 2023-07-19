/*-
 * #%L
 * Template Add-on
 * %%
 * Copyright (C) 2022 - 2023 Flowing Code
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

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.RouterLayout;

/**
 * LoginOverlay based Layout that can be used to display views with a similar layout as the
 * LoginOverlay. It also provides methods for replacing part of the contents of the overlay.
 *
 * @author mlopez
 */
@SuppressWarnings("serial")
public class LoginLayout extends LoginOverlay implements RouterLayout, ReplaceableLoginOverlay {

  private HasElement content;

  @Override
  public void showRouterLayoutContent(HasElement content) {
    RouterLayout.super.showRouterLayoutContent(content);
    this.content = content;
  }

  @Override
  public void removeRouterLayoutContent(HasElement oldContent) {
    RouterLayout.super.removeRouterLayoutContent(oldContent);
    content = null;
  }

  @Override
  protected void onAttach(AttachEvent attachEvent) {
    super.onAttach(attachEvent);
    setOpened(true);
    this.getElement()
        .executeJs(
            "setTimeout(()=>document.getElementById('vaadinLoginOverlayWrapper').getElementsByTagName('vaadin-login-form-wrapper')[0].replaceChildren())");
    this.getElement().appendChild(content.getElement());
    content.getElement().setAttribute("slot", "form");
    this.content
        .getElement()
        .executeJs(
            "setTimeout(()=>document.getElementById('vaadinLoginOverlayWrapper').getElementsByTagName('vaadin-login-form-wrapper')[0].appendChild(this))");
  }
}

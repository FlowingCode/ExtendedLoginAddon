/*-
 * #%L
 * Extended Login Add-on
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

import com.vaadin.flow.component.HasElement;

/**
 * Mixin interface providing methods for replacing contents of the LoginOverlay
 *
 * @author mlopez
 */
public interface ReplaceableLoginOverlay extends HasElement {

  default void replaceFormComponents(HasElement... withElement) {
    this.getElement()
        .executeJs(
            "setTimeout(()=>document.getElementById('vaadinLoginOverlayWrapper').getElementsByTagName('vaadin-login-form-wrapper')[0].getElementsByTagName('form')[0].replaceChildren())");
    for (HasElement we : withElement) {
      getElement().appendChild(we.getElement());
      this.getElement()
          .executeJs(
              "setTimeout(()=>document.getElementById('vaadinLoginOverlayWrapper').getElementsByTagName('vaadin-login-form-wrapper')[0].getElementsByTagName('form')[0].appendChild($0))",
              we.getElement());
    }
  }

  default void replaceHeaderComponent(HasElement withElement) {
    getElement().appendChild(withElement.getElement());
    this.getElement()
        .executeJs(
            "setTimeout(()=>document.getElementById('vaadinLoginOverlayWrapper').shadowRoot.querySelector('[part=\"brand\"]').replaceChildren())");
    this.getElement()
        .executeJs(
            "setTimeout(()=>document.getElementById('vaadinLoginOverlayWrapper').shadowRoot.querySelector('[part=\"brand\"]').appendChild($0))",
            withElement);
  }

  default void replaceForgotPassword(HasElement withElement) {
    withElement.getElement().setAttribute("slot", "forgot-password");
    getElement().appendChild(withElement.getElement());
    this.getElement()
        .executeJs(
            "setTimeout(()=>document.getElementById('vaadinLoginOverlayWrapper').getElementsByTagName('vaadin-login-form-wrapper')[0].querySelector('[slot=\\\"forgot-password\\\"]').remove())");
    this.getElement()
        .executeJs(
            "setTimeout(()=>document.getElementById('vaadinLoginOverlayWrapper').getElementsByTagName('vaadin-login-form-wrapper')[0].appendChild($0))",
            withElement);
  }
}

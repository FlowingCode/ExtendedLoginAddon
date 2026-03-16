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

/**
 * Utility class for LoginOverlay.
 */
class LoginOverlayUtils {

  private LoginOverlayUtils() {
    // Utility class
  }

  /**
   * Generates a JavaScript snippet that finds the overlay wrapper element.
   * Handles both Vaadin 24 and Vaadin 25+ compatibility.
   *
   * @param action the JavaScript code to execute on the overlay wrapper
   * @return the complete JavaScript string with the overlay wrapper lookup and
   *         the provided action
   */
  static String getOverlayWrapperScript(String action) {
    return """
        setTimeout(() => {
          var overlayWrapper = document.getElementById('vaadinLoginOverlayWrapper');
          if (!overlayWrapper) {
            var loginOverlay = document.querySelector('vaadin-login-overlay');
            if (loginOverlay && loginOverlay.shadowRoot) {
              overlayWrapper = loginOverlay.shadowRoot
                .querySelector('vaadin-login-overlay-wrapper');
            }
          }
          if (!overlayWrapper) return;
          %s
        });
        """.formatted(action);
  }

  /**
   * Generates a JavaScript snippet that finds the form element. Handles both
   * Vaadin 24 and Vaadin 25+ compatibility.
   *
   * @param action the JavaScript code to execute on the form
   * @return the complete JavaScript string with the form lookup and the provided
   *         action
   */
  static String getFormWrapperScript(String action) {
    return """
        setTimeout(() => {
          var overlayFormWrapper = document.getElementById('vaadinLoginOverlayWrapper');
          if (!overlayFormWrapper) {
            overlayFormWrapper = document.querySelector('vaadin-login-overlay');
          }
          if (!overlayFormWrapper) return;
          var form = overlayFormWrapper.querySelector('form');
          if (form) {
            %s
          }
        });
        """.formatted(action);
  }

  /**
   * Generates a JavaScript snippet that finds the form wrapper containing form
   * elements. Handles both Vaadin 24 and Vaadin 25+ compatibility.
   *
   * @param action the JavaScript code to execute on the form wrapper
   * @return the complete JavaScript string with the form wrapper lookup and the
   *         provided action
   */
  static String getLoginFormWrapperScript(String action) {
    return """
        setTimeout(() => {
          var overlayWrapper = document.getElementById('vaadinLoginOverlayWrapper');
          if (!overlayWrapper) {
            var loginOverlay = document.querySelector('vaadin-login-overlay');
            if (loginOverlay && loginOverlay.shadowRoot) {
              overlayWrapper = loginOverlay.shadowRoot
                .querySelector('vaadin-login-overlay-wrapper');
            }
          }
          if (!overlayWrapper) return;
          var formWrapper = overlayWrapper.querySelector('vaadin-login-form-wrapper');
          if (!formWrapper) return;
          %s
        });
        """.formatted(action);
  }

}

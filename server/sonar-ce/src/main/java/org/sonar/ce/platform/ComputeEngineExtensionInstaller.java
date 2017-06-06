/*
 * SonarQube
 * Copyright (C) 2009-2017 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.ce.platform;

import com.google.common.collect.ImmutableSet;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Collections;
import org.sonar.api.SonarRuntime;
import org.sonar.api.ce.ComputeEngineSide;
import org.sonar.api.config.Settings;
import org.sonar.api.server.ServerSide;
import org.sonar.core.platform.PluginRepository;
import org.sonar.server.plugins.ServerExtensionInstaller;

public class ComputeEngineExtensionInstaller extends ServerExtensionInstaller {
  public ComputeEngineExtensionInstaller(SonarRuntime sonarRuntime, PluginRepository pluginRepository, Settings settings) {
    super(sonarRuntime, pluginRepository, getSupportedAnnotations(settings));
  }

   private static Collection<Class<? extends Annotation>> getSupportedAnnotations(Settings settings) {
    if (settings.getBoolean("sonar.ce.componentLoading.strict")) {
      return Collections.singleton(ComputeEngineSide.class);
    }
    return ImmutableSet.of(ServerSide.class, ComputeEngineSide.class);
  }

}

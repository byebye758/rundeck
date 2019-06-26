package com.dtolabs.rundeck.server.plugins.services

import com.dtolabs.rundeck.core.plugins.PluggableProviderService
import com.dtolabs.rundeck.core.plugins.ServiceProviderLoader
import com.dtolabs.rundeck.plugins.ServiceNameConstants
import org.rundeck.core.plugins.PluginProviderServices
import com.dtolabs.rundeck.core.encrypter.PasswordUtilityEncrypter


class PasswordUtilityEncrypterPluginProviderService implements PluginProviderServices {

    @Override
    def <T> boolean hasServiceFor(Class<T> serviceType, String serviceName) {
        return serviceType == PasswordUtilityEncrypter.class && serviceName.equals(ServiceNameConstants.PasswordUtilityEncrypter)
    }

    @Override
    def <T> PluggableProviderService<T> getServiceProviderFor(Class<T> serviceType, String serviceName, ServiceProviderLoader loader) {
        if(serviceType == PasswordUtilityEncrypter.class && ServiceNameConstants.PasswordUtilityEncrypter.equals(serviceName))
            return (PluggableProviderService<T>)new PasswordUtilityEncrypterProviderService(loader)
        return null;
    }
}

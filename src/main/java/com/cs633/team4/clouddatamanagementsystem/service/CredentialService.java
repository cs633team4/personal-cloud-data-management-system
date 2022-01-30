package com.cs633.team4.clouddatamanagementsystem.service;

import com.cs633.team4.clouddatamanagementsystem.mapper.CredentialMapper;
import com.cs633.team4.clouddatamanagementsystem.model.Credential;
import com.cs633.team4.clouddatamanagementsystem.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {

    private final CredentialMapper credentialMapper;
    private final EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public void addCredential(Credential credential) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);
        Credential newCredential = new Credential(0, credential.getUrl(), credential.getUsername(),
                                                  encodedKey, encryptedPassword, credential.getUserId());
        credentialMapper.insert(newCredential);
    }

    public Integer updateCredential(Credential credential) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);
        credential.setPassword(encryptedPassword);
        credential.setKey(encodedKey);
        return credentialMapper.update(credential);
    }

    public Integer deleteCredential(Integer credentialId) {
        return credentialMapper.delete(credentialId);
    }

    public Credential getCredential(Integer credentialId) {
        return credentialMapper.getCredential(credentialId);
    }

    public Credential getCredentialByUserAndUrl(Integer userid, String url) {
        return credentialMapper.getCredentialByUserAndUrl(userid, url);
    }

    public List<Credential> getAllCredentials(User user) {
        return credentialMapper.getCredentialsByUser(user.getUserId());
    }

}


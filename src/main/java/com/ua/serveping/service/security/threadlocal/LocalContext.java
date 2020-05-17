package com.ua.serveping.service.security.threadlocal;

import com.ua.serveping.service.models.domains.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalContext {
    private Users users;
}

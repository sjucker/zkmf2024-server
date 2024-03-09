package ch.zkmf2024.server.rest;

import ch.zkmf2024.server.AbstractIntegrationTest;
import ch.zkmf2024.server.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class WebhookEndpointTest extends AbstractIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MailService mailService;

    @Test
    void test() throws Exception {
        mockMvc.perform(post("/public/webhook")
                                .contentType(APPLICATION_JSON_VALUE)
                                .content("""
                                                 {
                                                   "id": "4397fe1d-ff26-4455-a141-6299e434e25e",
                                                   "data": {
                                                     "id": "ad498859-df59-4ec1-8f28-c07497fb7fa8",
                                                     "app": {
                                                       "id": "71734fc7-f69c-48ad-bfdd-285ca850977e",
                                                       "name": "zkmf2024-server-staging"
                                                     },
                                                     "slug": null,
                                                     "user": {
                                                       "id": "2ea79798-a6aa-475b-98ba-1aa38d042acc",
                                                       "email": "stefan.jucker@gmail.com"
                                                     },
                                                     "stack": "heroku-22",
                                                     "status": "pending",
                                                     "release": null,
                                                     "metadata": {},
                                                     "buildpacks": [
                                                       {
                                                         "url": "https://buildpack-registry.s3.amazonaws.com/buildpacks/heroku/java.tgz"
                                                       }
                                                     ],
                                                     "created_at": "2024-03-09T09:58:57Z",
                                                     "updated_at": "2024-03-09T09:58:57Z",
                                                     "source_blob": {
                                                       "url": "https://codeload.github.com/sjucker/zkmf2024-server/legacy.tar.gz/f981f713e628f8e77718366318792642435d8600",
                                                       "version": "f981f713e628f8e77718366318792642435d8600",
                                                       "checksum": null
                                                     },
                                                     "output_stream_url": "https://build-output.heroku.com/streams/71/71734fc7-f69c-48ad-bfdd-285ca850977e/logs/ad/ad498859-df59-4ec1-8f28-c07497fb7fa8.log?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAZSXS6CXK3PQ5Y6GY%2F20240309%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20240309T095857Z&X-Amz-Expires=86400&X-Amz-SignedHeaders=host&X-Amz-Signature=a9c204428241bab7af7f7aa81103075bf21f1d37140f6ae949820d56ac9f4c6a"
                                                   },
                                                   "actor": {
                                                     "id": "2ea79798-a6aa-475b-98ba-1aa38d042acc",
                                                     "email": "stefan.jucker@gmail.com"
                                                   },
                                                   "action": "create",
                                                   "version": "application/vnd.heroku+json; version=3",
                                                   "resource": "build",
                                                   "sequence": null,
                                                   "created_at": "2024-03-09T09:58:57+00:00",
                                                   "updated_at": "2024-03-09T09:58:57+00:00",
                                                   "published_at": "2024-03-09T09:58:58Z",
                                                   "previous_data": {},
                                                   "webhook_metadata": {
                                                     "attempt": {
                                                       "id": "29f31b5c-e208-4825-af59-8b4d4b037354"
                                                     },
                                                     "delivery": {
                                                       "id": "b60d3410-9c84-41bf-aa6d-6328104ee977"
                                                     },
                                                     "event": {
                                                       "id": "4397fe1d-ff26-4455-a141-6299e434e25e",
                                                       "include": "api:build"
                                                     },
                                                     "webhook": {
                                                       "id": "b19371f5-d080-4244-a7dd-875f7a1527dd"
                                                     }
                                                   }
                                                 }"""))
               .andExpect(status().isNoContent());

        verify(mailService).sendWebhookEmail("create", "build", "pending", "zkmf2024-server-staging", "f981f713e628f8e77718366318792642435d8600");
    }

    @Test
    void test2() throws Exception {
        mockMvc.perform(post("/public/webhook")
                                .contentType(APPLICATION_JSON_VALUE)
                                .content("""
                                                   {
                                                   "id": "d51b5b55-c2f8-4b71-8de6-73510e1e3f91",
                                                   "data": {
                                                     "id": "ce86db34-d47d-4296-b4c5-d63ebefb45c7",
                                                     "app": {
                                                       "id": "71734fc7-f69c-48ad-bfdd-285ca850977e",
                                                       "name": "zkmf2024-server-staging"
                                                     },
                                                     "slug": {
                                                       "id": "c6594a56-da45-4f2e-820f-c601e010b12c",
                                                       "commit": "203e7560ee53922a7011c49e169ac10b61614e64",
                                                       "commit_description": ""
                                                     },
                                                     "user": {
                                                       "id": "2ea79798-a6aa-475b-98ba-1aa38d042acc",
                                                       "email": "stefan.jucker@gmail.com"
                                                     },
                                                     "stack": "heroku-22",
                                                     "status": "succeeded",
                                                     "release": {
                                                       "id": "761eba0c-8bda-45db-8deb-286d95a11584",
                                                       "version": 298
                                                     },
                                                     "metadata": {},
                                                     "buildpacks": [
                                                       {
                                                         "url": "https://buildpack-registry.s3.amazonaws.com/buildpacks/heroku/java.tgz"
                                                       }
                                                     ],
                                                     "created_at": "2024-03-09T10:30:44Z",
                                                     "updated_at": "2024-03-09T10:34:35Z",
                                                     "source_blob": {
                                                       "url": "https://codeload.github.com/sjucker/zkmf2024-server/legacy.tar.gz/203e7560ee53922a7011c49e169ac10b61614e64",
                                                       "version": "203e7560ee53922a7011c49e169ac10b61614e64",
                                                       "checksum": null
                                                     },
                                                     "output_stream_url": "https://build-output.heroku.com/streams/71/71734fc7-f69c-48ad-bfdd-285ca850977e/logs/ce/ce86db34-d47d-4296-b4c5-d63ebefb45c7.log?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAZSXS6CXK3PQ5Y6GY%2F20240309%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20240309T103435Z&X-Amz-Expires=86400&X-Amz-SignedHeaders=host&X-Amz-Signature=cca6f4ebf3727ba1e4d338c794fa0bc0f975bd242113acf7e9ff4240bb0a7a1e"
                                                   },
                                                   "actor": {
                                                     "id": "2ea79798-a6aa-475b-98ba-1aa38d042acc",
                                                     "email": "stefan.jucker@gmail.com"
                                                   },
                                                   "action": "update",
                                                   "version": "application/vnd.heroku+json; version=3",
                                                   "resource": "build",
                                                   "sequence": null,
                                                   "created_at": "2024-03-09T10:34:35+00:00",
                                                   "updated_at": "2024-03-09T10:34:35+00:00",
                                                   "published_at": "2024-03-09T10:34:35Z",
                                                   "previous_data": {},
                                                   "webhook_metadata": {
                                                     "attempt": {
                                                       "id": "ce3464c7-1940-498d-9507-dbf81b0acf52"
                                                     },
                                                     "delivery": {
                                                       "id": "4d511e42-bf9c-4d6e-be80-b347c5587c39"
                                                     },
                                                     "event": {
                                                       "id": "d51b5b55-c2f8-4b71-8de6-73510e1e3f91",
                                                       "include": "api:build"
                                                     },
                                                     "webhook": {
                                                       "id": "b19371f5-d080-4244-a7dd-875f7a1527dd"
                                                     }
                                                   }
                                                 }
                                                 """))
               .andExpect(status().isNoContent());

        verify(mailService).sendWebhookEmail("update", "build", "succeeded", "zkmf2024-server-staging", "203e7560ee53922a7011c49e169ac10b61614e64");
    }

}

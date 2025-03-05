package com.tbank.learn.carsharing.controller.client

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.ninjasquad.springmockk.SpykBean
import com.tbank.learn.carsharing.BaseConfigTest
import com.tbank.learn.carsharing.controller.ClientController
import com.tbank.learn.carsharing.dto.ApiErrorResponse
import com.tbank.learn.carsharing.dto.client.request.ClientUpsertDto
import com.tbank.learn.carsharing.dto.communication.request.EmailUpsertDto
import com.tbank.learn.carsharing.dto.communication.request.PhoneUpsertDto
import com.tbank.learn.carsharing.model.communication.Email
import com.tbank.learn.carsharing.model.communication.Phone
import com.tbank.learn.carsharing.model.user.Client
import com.tbank.learn.carsharing.repository.ClientRepository
import com.tbank.learn.carsharing.repository.communication.EmailRepository
import com.tbank.learn.carsharing.repository.communication.PhoneRepository
import com.tbank.learn.carsharing.service.ClientService
import io.mockk.Called
import io.mockk.every
import io.mockk.verify
import io.mockk.verifyAll
import org.hamcrest.Matchers.startsWith
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.util.*
import java.util.stream.Stream

@ExtendWith(SpringExtension::class)
@Import(value = [BaseConfigTest::class])
@WebMvcTest(controllers = [ClientController::class])
class ClientControllerTest(
		@Autowired val mockMvc: MockMvc
) {
		//нужны clientService
		@MockkBean
		lateinit var clientRepository: ClientRepository
		@MockkBean
		lateinit var phoneRepository: PhoneRepository
		@MockkBean
		lateinit var emailRepository: EmailRepository
		//нужен контроллеру
		@SpykBean //@InjectMockKs
		lateinit var clientService: ClientService
		
		@Autowired
		lateinit var mapper: ObjectMapper //jacksonObjectMapper()
		
		/** 200. GET. Клиент найден **/
		@Test
		@DisplayName("GET /client/[UUID] -> 200")
		fun getClientById_200() {
				val id = UUID(1, 1).toString()
				val clientFst = Client("Фамилия", "Имя", "Отчество")
				//every { clientService.getById(UUID(1, 1)) } returns ClientAllResponse(clientFst)
				every { phoneRepository.findAllByParentId(any<UUID>()) } returns LinkedHashSet.newLinkedHashSet(0)
				every { emailRepository.findAllByParentId(any<UUID>()) } returns LinkedHashSet.newLinkedHashSet(0)
				every { clientRepository.findById(any<UUID>()) } answers {
						arg<UUID>(0).let {
								clientFst.setId(it)
								Optional.of(clientFst)
						}
				}
				
				mockMvc.perform(get("/client/$id")
						.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk)
						.andExpect(content().contentType(MediaType.APPLICATION_JSON))
						.andExpect(jsonPath("$.lastName").value(clientFst.lastName))
						.andExpect(jsonPath("$.id").value(id))
				
				verifyAll {
						phoneRepository.findAllByParentId(any<UUID>())
						emailRepository.findAllByParentId(any<UUID>())
						clientRepository.findById(any<UUID>())
				}
		}
		
		/** 400. GET. Клиент НЕ найден **/
		@Test
		@DisplayName("GET /client/[UUID] -> 400 (Client not found)")
		fun getClientById_400() {
				every { clientRepository.findById(any<UUID>()) } returns Optional.ofNullable(null)
				
				mockMvc.perform(get("/client/${UUID(1, 1)}"))
						.andExpect(status().isBadRequest())
						.andExpect(content().contentType(MediaType.APPLICATION_JSON))
						.andExpect(jsonPath("$.errorMessage").value("Клиент не найден!"))
				
				verify {
						clientRepository.findById(any<UUID>())
						listOf(emailRepository, phoneRepository) wasNot Called
				}
		}
		
		/** 200. POST. Создание и получение с айди */
		@ParameterizedTest(name = "POST /client -> 200")
		@MethodSource("goodNewClientDto")
		fun createClientAndGet_200(clientDto: ClientUpsertDto) {
				every { clientRepository.save(any<Client>()) } answers {
						arg<Client>(0).also { it.setId(UUID.randomUUID()) }
				}
				every { phoneRepository.save(any<Phone>()) } answers {
						arg<Phone>(0).also { it.setId(UUID.randomUUID()) }
				}
				every { emailRepository.save(any<Email>()) } answers {
						arg<Email>(0).also { it.setId(UUID.randomUUID()) }
				}

				mockMvc.perform(post("/client")
						.content(mapper.writeValueAsString(clientDto))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().`is`(201)) //status().is2xxSuccessful
						.andExpect(content().contentType(MediaType.APPLICATION_JSON))
						.andExpect(jsonPath("$.id").isNotEmpty)
						.andExpect(jsonPath("$.lastName").value(clientDto.lastName))
						.andExpect(jsonPath("$.firstName").value(clientDto.firstName))
				//todo email\phone и что ещё появится
				
				verify {
						clientRepository.save(any<Client>())
						//listOf(emailRepository, phoneRepository) wasNot Called
				}
		}
		
		/** 200. PUT. Апдейт и получение */
		@ParameterizedTest(name = "PUT /client/update -> 200")
		@MethodSource("goodExistClientDto")
		fun updateClientAndGet_200(clientDto: ClientUpsertDto) {
				val clientExisting = Client("", "", "") //пустой клиент из бд (для обновления\сравнения с ДТО клиентом)
				every { clientRepository.save(any<Client>()) } answers {
						arg<Client>(0).also { it.version = clientDto.version+1 }
				}
				every { phoneRepository.save(any<Phone>()) } answers {
						arg<Phone>(0) //как то не удобно версионность подставлять в лист
				}
				every { emailRepository.save(any<Email>()) } answers {
						arg<Email>(0) //как то не удобно версионность подставлять в лист
				}
				every { phoneRepository.findAllByParentId(any<UUID>()) } returns LinkedHashSet.newLinkedHashSet(0)
				every { emailRepository.findAllByParentId(any<UUID>()) } returns LinkedHashSet.newLinkedHashSet(0)
				every { clientRepository.findById(any<UUID>()) } answers {
						arg<UUID>(0).let {
								clientExisting.setId(it)
								Optional.of(clientExisting)
						}
				}
				
				assertNotEquals(clientExisting.lastName, clientDto.lastName) //пустой lastName != пройденному валидацию ДТО lastName
				
				mockMvc.perform(put("/client/update")
						.content(mapper.writeValueAsString(clientDto))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk)
						.andExpect(content().contentType(MediaType.APPLICATION_JSON))
						.andExpect(jsonPath("$.id").value(clientDto.id.toString()))
						.andExpect(jsonPath("$.version").value(clientDto.version+1))
						.andExpect(jsonPath("$.lastName").value(clientDto.lastName))
						.andExpect(jsonPath("$.firstName").value(clientDto.firstName))
				//todo email\phone и что ещё появится
				
				assertEquals(clientExisting.lastName, clientDto.lastName) //клиент обновился на данные с ДТО
				
				verify {
						clientRepository.save(any<Client>())
						//listOf(emailRepository, phoneRepository) wasNot Called
				}
		}
		
		/** 200. PUT. Апдейт без зависимотей и получение */
		@ParameterizedTest(name = "PUT /client/update/light -> 200")
		@MethodSource("goodExistClientLightDto")
		fun updateClientLightAndGet_200(clientDto: ClientUpsertDto) {
				val clientExisting = Client("", "", "") //пустой клиент из бд (для обновления\сравнения с ДТО клиентом)
				every { clientRepository.save(any<Client>()) } answers {
						arg<Client>(0).also { it.version = clientDto.version+1 }
				}
				every { clientRepository.findById(any<UUID>()) } answers {
						arg<UUID>(0).let {
								clientExisting.setId(it)
								Optional.of(clientExisting)
						}
				}
				
				mockMvc.perform(put("/client/update/light")
						.content(mapper.writeValueAsString(clientDto))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk)
						.andExpect(content().contentType(MediaType.APPLICATION_JSON))
						.andExpect(jsonPath("$.id").value(clientDto.id.toString()))
						.andExpect(jsonPath("$.version").value(clientDto.version+1))
						.andExpect(jsonPath("$.lastName").value(clientDto.lastName))
						.andExpect(jsonPath("$.firstName").value(clientDto.firstName))
				
				verify {
						clientRepository.save(any<Client>())
				}
		}
		
		/** 400. POST. Валидация нового клиента */
		@ParameterizedTest(name = "POST /client -> 400 (Valid error)")
		@MethodSource("badClientDto")
		fun createClient_NotValid_400(clientDto: ClientUpsertDto, fieldError: Array<String>) {
				mockMvc.perform(post("/client")
						.content(mapper.writeValueAsString(clientDto))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isBadRequest())
						.andExpect(content().contentType(MediaType.APPLICATION_JSON))
						.andReturn().apply {
								val errResponse = mapper.readValue(this.response.contentAsString, Array<ApiErrorResponse>::class.java)
								assertEquals(errResponse.size, fieldError.size)
								errResponse.forEach { assert(fieldError.contains(it.errorCode)) }
						} //.andExpect(jsonPath("$[*].errorCode", containsInAnyOrder(fieldError)))
				
				verify {
						clientRepository wasNot Called //clientService
				}
		}
		
		/** 400. POST. JSON parse error */
		@Test
		@DisplayName("POST /client -> 400 (JSON parse error)")
		fun createClient_BadJSON_400() {
				mockMvc.perform(post("/client")
						.content("{ \"firstName\": \"1поле\" }")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isBadRequest())
						.andExpect(content().contentType(MediaType.APPLICATION_JSON))
						.andExpect(jsonPath("$.errorMessage", startsWith("JSON parse error")))
		}
		
		/** 200. GET. findAll(any<Pageable>()) */
		@ParameterizedTest(name = "GET /list?pageSize=[Int] -> 200")
		@ValueSource(ints = [2, 5, 10])
		fun listOf2Client_200(pages: Int) {
				val clientFst = Client("Фамилия", "Имя", "Отчество")
				val clientSnd = Client("Фамилия2", "Имя2", "Отчество2")
				//every { clientService.getList(Pageable.ofSize(2)) } returns listOf(clientFst, clientSnd)
				every { clientRepository.findAll(any<Pageable>()) } returns PageImpl(listOf(clientFst, clientSnd))
				
				mockMvc.perform(get("/client/list?pageSize=$pages").accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk)
						.andExpect(content().contentType(MediaType.APPLICATION_JSON))
						.andExpect(jsonPath("$.length()").value(2))
						.andExpect(jsonPath("$.[0].lastName").value(clientFst.lastName))
						.andExpect(jsonPath("$.[1].lastName").value(clientSnd.lastName))
		}
		
		/** 200. DELETE. TODO */
		fun deleteClient_200() {
				mockMvc.perform(delete("/client/${UUID(1, 1)}"))
						.andExpect(status().isOk())
		}
		
		/** Static */
		companion object {
				val EMPTY_PHONE = Phone(UUID(0L,0L), "", false)
				val EMPTY_EMAIL = Email(UUID(0L,0L), "", false)
				val EMPTY_CLIENT = Client("", "", "")

				/** Под создание\обновление НЕвалидного клиента.
				 * Arguments содержит:
				 * (1) дто клиента с невалид полями,
				 * (2) массив этих невалидных полей, которые попадут в [ApiErrorResponse.errorCode] */
				@JvmStatic
				fun badClientDto(): Stream<Arguments> = Stream.of(
						//пустой lastName
						Arguments.of(ClientUpsertDto(0, null, "", "Имя", "Отчество",
								ArrayList<EmailUpsertDto>(0),
								ArrayList<PhoneUpsertDto>(0))
								, arrayOf("lastName")),
						//пустой firstName
						Arguments.of(ClientUpsertDto(0, null, "Фамилия", "", "",
								ArrayList<EmailUpsertDto>(0),
								ArrayList<PhoneUpsertDto>(0))
								, arrayOf("firstName")),
						//пустой
						Arguments.of(ClientUpsertDto(0, null, "", "", "",
								ArrayList<EmailUpsertDto>(0),
								ArrayList<PhoneUpsertDto>(0))
								, arrayOf("lastName", "firstName")),
						//Не кириллица в имени
						Arguments.of(ClientUpsertDto(0, null, "321", "!,_", "MidName",
								ArrayList<EmailUpsertDto>(0),
								ArrayList<PhoneUpsertDto>(0))
								, arrayOf("lastName", "firstName", "middleName"))
				)
				
				/** Под создание валидного клиента */
				@JvmStatic
				fun goodNewClientDto(): Stream<Arguments> = Stream.of(
						Arguments.of(ClientUpsertDto(0, null, "Фамилия", "Имя", "Отчество",
								ArrayList<EmailUpsertDto>(0),
								ArrayList<PhoneUpsertDto>(0))
						))
				
				/** Под обновление валидного клиента */
				@JvmStatic
				fun goodExistClientDto(): Stream<Arguments> = Stream.of(
						Arguments.of(ClientUpsertDto(1, UUID.randomUUID(), "Фамилия", "Имя", "Отчество",
								ArrayList<EmailUpsertDto>(0),
								ArrayList<PhoneUpsertDto>(0))
						))
				
				/** Под обновление валидного клиента без зависимостей */
				@JvmStatic
				fun goodExistClientLightDto(): Stream<Arguments> = Stream.of(
						Arguments.of(ClientUpsertDto(1, UUID.randomUUID(), "Фамилия", "Имя", "Отчество")))
		}

}
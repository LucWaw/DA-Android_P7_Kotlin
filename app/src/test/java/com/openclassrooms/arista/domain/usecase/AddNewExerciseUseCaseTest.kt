package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.data.repository.ExerciseRepository
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.model.ExerciseCategory
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.time.LocalDateTime

@RunWith(JUnit4::class)
class AddNewExerciseUseCaseTest{
    @Mock
    private lateinit var exerciseRepository: ExerciseRepository


    private lateinit var getAllExercisesUseCase: GetAllExercisesUseCase


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getAllExercisesUseCase = GetAllExercisesUseCase(exerciseRepository)
    }

    @After
    fun tearDown() {
        Mockito.framework().clearInlineMocks()
    }

    @Test
    fun `when add a exercice, exercice should be in return list`() = runBlocking {
        // Arrange
        val exerciceToAdd = Exercise(
            startTime = LocalDateTime.now(),
            duration = 30,
            category = ExerciseCategory.Running,
            intensity = 5
        )
        val fakeExercises = mutableListOf(
            Exercise(
                startTime = LocalDateTime.now().plusHours(1),
                duration = 45,
                category = ExerciseCategory.Riding,
                intensity = 7
            )
        )
        Mockito.`when`(exerciseRepository.getAllExercises()).thenReturn(fakeExercises)
        Mockito.`when`(exerciseRepository.addExercise(exerciceToAdd)).then{
            fakeExercises.add(exerciceToAdd)
        }


        // Act
        val result = getAllExercisesUseCase.execute()


        // Assert
        assertEquals(fakeExercises, result)
    }
}
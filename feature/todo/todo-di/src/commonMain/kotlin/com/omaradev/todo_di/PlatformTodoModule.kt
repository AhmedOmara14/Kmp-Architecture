package com.omaradev.todo_di

import com.omaradev.todo_data.repository.TodoRepositoryImpl
import com.omaradev.todo_domain.repositories.TodoRepository
import com.omaradev.todo_domain.usecase.DeleteTaskUseCase
import com.omaradev.todo_domain.usecase.GetLoggedUserIdUseCase
import com.omaradev.todo_domain.usecase.GetTaskByIdUseCase
import com.omaradev.todo_domain.usecase.ObserveTasksByUserIdUseCase
import com.omaradev.todo_domain.usecase.SaveTaskUseCase
import com.omaradev.todo_ui.home.viewmodel.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

fun platformTodoModule() = module {
    single<TodoRepository> { TodoRepositoryImpl(get(),get()) }
    factory { SaveTaskUseCase(get()) }
    factory { DeleteTaskUseCase(get()) }
    factory { GetLoggedUserIdUseCase(get()) }
    factory { GetTaskByIdUseCase(get()) }
    factory { ObserveTasksByUserIdUseCase(get()) }
    viewModelOf(::HomeViewModel)
}
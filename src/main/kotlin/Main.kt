// TODO: Implement:
//  - Reversing built pipeline
//  - Mapping results of intermediate stages
//  - Generic stages
package io.github.vp

import kotlin.reflect.KClass

typealias CallNext<I, O> = (I) -> O

interface Stage<I, O, R> {
    fun call(entity: I, next: CallNext<O, R>): R
}

interface PipelineBuilder<I, Origin, F> {
    fun <O, S : Stage<I, O, F>> add(kClass: KClass<S>): PipelineBuilder<O, Origin, F>

    fun build(): Pipeline<Origin, F>
}

interface Pipeline<I, O> {
    fun process(entity: I): O
}


interface CreatePaymentData
interface Payment
interface SavedPayment
interface UpdatedPayment
interface Result

class CreatePaymentStage : Stage<CreatePaymentData, Payment, Result> {
    override fun call(entity: CreatePaymentData, next: CallNext<Payment, Result>): Result {
        TODO("Not yet implemented")
    }
}

class SavePaymentStage : Stage<Payment, SavedPayment, Result> {
    override fun call(entity: Payment, next: CallNext<SavedPayment, Result>): Result {
        TODO("Not yet implemented")
    }
}

class UpdateSavePaymentStage : Stage<SavedPayment, UpdatedPayment, Result> {
    override fun call(entity: SavedPayment, next: CallNext<UpdatedPayment, Result>): Result {
        TODO("Not yet implemented")
    }
}

class ReplicateUpdatedPayment : Stage<UpdatedPayment, UpdatedPayment, Result> {
    override fun call(entity: UpdatedPayment, next: CallNext<UpdatedPayment, Result>): Result {
        TODO("Not yet implemented")
    }
}

fun <I, R> createPipeline(): PipelineBuilder<I, I, R> {
    TODO("Not yet implemented")
}

fun main() {
    val pipeline: Pipeline<CreatePaymentData, Result> =
        createPipeline<CreatePaymentData, Result>()
            .add(CreatePaymentStage::class)
            .add(SavePaymentStage::class)
            .add(UpdateSavePaymentStage::class)
            .add(ReplicateUpdatedPayment::class)
            .build()

    val res: Result = pipeline.process(object : CreatePaymentData {})
}

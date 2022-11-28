// TODO:
//  - The actual building of pipelines
//  - Generic stages

package io.github.vp

import kotlin.reflect.KClass

typealias CallNext<I, O> = (I) -> Either<PipelineError, O>

interface Stage<I, O> {
    fun <R> call(entity: I, next: CallNext<O, R>): Either<PipelineError, R>
}

interface PipelineBuilder<Origin, In> {
    fun <Out, S : Stage<In, Out>> add(kClass: KClass<S>): PipelineBuilder<Origin, Out>

    fun build(): Pipeline<Origin, In>
}

interface Pipeline<Origin, Final> {
    fun process(entity: Origin): Final
}

interface Either<L, R>
interface PipelineError
interface CreatePaymentData
interface Payment
interface SavedPayment
interface UpdatedPayment
interface ReplicatedPayment

class CreatePaymentStage : Stage<CreatePaymentData, Payment> {
    override fun <R> call(entity: CreatePaymentData, next: CallNext<Payment, R>): Either<PipelineError, R> {
        TODO("Not yet implemented")
    }
}

class SavePaymentStage : Stage<Payment, SavedPayment> {
    override fun <R> call(entity: Payment, next: CallNext<SavedPayment, R>): Either<PipelineError, R> {
        TODO("Not yet implemented")
    }
}

class UpdateSavedPaymentStage : Stage<SavedPayment, UpdatedPayment> {
    override fun <R> call(entity: SavedPayment, next: CallNext<UpdatedPayment, R>): Either<PipelineError, R> {
        TODO("Not yet implemented")
    }
}

class ReplicateUpdatedPayment : Stage<UpdatedPayment, ReplicatedPayment> {
    override fun <R> call(entity: UpdatedPayment, next: CallNext<ReplicatedPayment, R>): Either<PipelineError, R> {
        TODO("Not yet implemented")
    }
}

fun <Origin> createPipeline(): PipelineBuilder<Origin, Origin> {
    TODO("Not yet implemented")
}

fun main() {
    val pipeline = createPipeline<CreatePaymentData>()
            .add(CreatePaymentStage::class)
            .add(SavePaymentStage::class)
            .add(UpdateSavedPaymentStage::class)
            .add(ReplicateUpdatedPayment::class)
            .build()

    val res: ReplicatedPayment = pipeline.process(object : CreatePaymentData {})
}

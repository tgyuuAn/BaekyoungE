package com.tgyuu.network.source.mentoring

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.tgyuu.network.constant.RECRUITING_MENTOR_COLLECTION
import com.tgyuu.network.model.mentoring.MentorInfoRequest
import com.tgyuu.network.model.mentoring.MentorInfoResponse
import com.tgyuu.network.util.await
import javax.inject.Inject

class MentoringDataSourceImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
) : MentoringDataSource {
    override suspend fun postMentorInfo(mentorInfoRequest: MentorInfoRequest): Result<Unit> =
        runCatching {
            firebaseFirestore.collection(RECRUITING_MENTOR_COLLECTION)
                .document(mentorInfoRequest.userId)
                .set(mentorInfoRequest)
                .await()
        }

    override suspend fun deleteMentorInfo(userId: String): Result<Unit> =
        runCatching {
            firebaseFirestore.collection(RECRUITING_MENTOR_COLLECTION)
                .document(userId)
                .delete()
                .await()
        }

    override suspend fun getMentorInfo(userId: String): Result<MentorInfoResponse> =
        runCatching {
            val document = firebaseFirestore.collection(RECRUITING_MENTOR_COLLECTION)
                .document(userId)
                .get()
                .await()

            checkNotNull(document.toObject<MentorInfoResponse>())
        }

    override suspend fun getAllMentorsInfo(): Result<List<MentorInfoResponse>> =
        runCatching {
            firebaseFirestore.collection(RECRUITING_MENTOR_COLLECTION)
                .get()
                .await()
                .documents
                .map { document ->
                    document.toObject<MentorInfoResponse>()
                        ?: throw IllegalArgumentException("Invalid mentor info")
                }
        }
}

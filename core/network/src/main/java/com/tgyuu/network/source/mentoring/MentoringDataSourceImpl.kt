package com.tgyuu.network.source.mentoring

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.tgyuu.network.constant.RECRUTING_MENTOR_COLLECTION
import com.tgyuu.network.model.mentoring.MentorInfoRequest
import com.tgyuu.network.model.mentoring.MentorInfoResponse
import com.tgyuu.network.util.await
import javax.inject.Inject

class MentoringDataSourceImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
) : MentoringDataSource {
    override suspend fun postMentor(mentorInfoRequest: MentorInfoRequest): Result<Unit> =
        runCatching {
            firebaseFirestore.collection(RECRUTING_MENTOR_COLLECTION)
                .document(mentorInfoRequest.userId)
                .set(mentorInfoRequest)
                .await()
        }

    override suspend fun getAllMentors(): Result<List<MentorInfoResponse>> =
        runCatching {
            firebaseFirestore.collection(RECRUTING_MENTOR_COLLECTION)
                .get()
                .await()
                .documents
                .map { document ->
                    document.toObject<MentorInfoResponse>() ?: throw IllegalArgumentException("Invalid mentor info")
                }
        }
}
